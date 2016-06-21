package caldeiraVapor.planta;

import java.util.logging.Level;
import java.util.logging.Logger;
import caldeiraVapor.planta.*;
import caldeiraVapor.mensagens.*;

public class CaldeiraVapor extends Thread {
    private Boolean caldeiraLigada;
    private Bomba[] arrayBombas;
    private BotaoEmergencia botao;
    private SensorNivelAgua sensorAgua;
    private SensorVapor sensorVapor;
    private ValvulaSaidaCaldeira valvula;

    private FilaPlantaParaControle filaParaControle;
    private FilaControleParaPlanta filaDoControle;

    private int totalBombas;
    private int temperatura;
    private int capacidade;
    private int mode;
    
    public CaldeiraVapor(FilaPlantaParaControle paraControle, FilaControleParaPlanta doControle) {

        this.filaParaControle = paraControle;
        this.filaDoControle = doControle;

        totalBombas = 4;
        arrayBombas = new Bomba[totalBombas];
        for(int i = 0 ; i < totalBombas; i++) {
            arrayBombas[i] = new Bomba();
        }
        capacidade = 1000; //Litros
        temperatura = 100; //celsius
        botao = new BotaoEmergencia();
        sensorAgua = new SensorNivelAgua();
        sensorVapor = new SensorVapor();
        valvula = new ValvulaSaidaCaldeira();
        caldeiraLigada =true;

        //PRIMEIRA COISA QUANDO A CALDEIRA É LIGADA
        MensagemDaPlanta msg = new MensagemDaPlanta(TipoDeMensagem.STEAM_BOILER_WAITING);
        msg.setConteudo(1); // SETADO MODO INICIANDO
        filaParaControle.post(msg);
        mode = 1;
    }
    
    public synchronized void setBombaAberta(int i, String aberta) {
        if(aberta.equals("true"))
            arrayBombas[i].abre();
        else
            arrayBombas[i].fecha();
            
    }
    
    public synchronized void setValvula(String aberta) {
        if(aberta.equals("true")) {
            System.out.println("ABERTAVALVULA "+aberta);
            valvula.setAberta(true);
        } else {
            valvula.setAberta(false);
        }
    }
    
    public synchronized int getNivel() {
        return sensorAgua.getNivel();
    }
    
    public synchronized int getCapacidade() {
        return capacidade;
    }
    
    public synchronized int getBombaAbertas() {
        int cont = 0;
        for(int i = 0; i < totalBombas; i++) {
            if(arrayBombas[i].isBombaAberta())
                cont++;
        }
        return cont;
    }
    
    public boolean getBombaAberta(int i) {
        if (i >= 0 && i < 4)
            return arrayBombas[i].isBombaAberta();
        else 
            throw new RuntimeException();
    }
    
    public void estragaBomba(int i) {
        if (i >= 0 && i < 4)
            arrayBombas[i].estragaBomba();
        else 
            throw new RuntimeException();
    }
    
    public void consertaBomba(int i) {
        if (i >= 0 && i < 4)
            arrayBombas[i].consertaBomba();
        else 
            throw new RuntimeException();
    }
    
    public synchronized int getFluxoVapor() {
            return sensorVapor.getFluxo();
    }
    
    public synchronized int getModo() {
            return mode;
    }
    
    public synchronized Boolean getValvula() {
            return valvula.getAberta();
    }
    
    public synchronized int getFluxoValvula() {
            return valvula.getFluxo();
    }

    private void calculaNivel() {
        int variacao = 0;

        for (int i = 0; i < totalBombas; i++) {
            if (arrayBombas[i].isBombaAberta()) {
                variacao += arrayBombas[i].getVazao();
            }
        }

        variacao -= sensorVapor.getFluxo();

        if (valvula.getAberta()) {
            variacao -= valvula.getFluxo();
        }

        int nivelAtual = sensorAgua.getNivel();
        sensorAgua.setNivel(nivelAtual + variacao);
    }
    
    @Override
    public void run() {
        while(caldeiraLigada) {
            
            // Recalcula o nivel da agua
            calculaNivel();
            //System.out.println("Nível: " + sensorAgua.getNivel());
            //System.out.println("Fluxo: "+ sensorVapor.getFluxo());
            //System.out.println("FluxoValvula: "+ valvula.getFluxo() + " Abrto? "+valvula.getAberta());
            
            // Despacha mensagens
            if (filaParaControle.isEmpty()) {
                MensagemDaPlanta msg;

                msg = new MensagemDaPlanta(TipoDeMensagem.LEVEL);
                msg.setConteudo(sensorAgua.getNivel());
                filaParaControle.post(msg);

                msg = new MensagemDaPlanta(TipoDeMensagem.STEAM);
                msg.setConteudo(sensorVapor.getFluxo());
                filaParaControle.post(msg);
                
                for (int i = 0; i < 4; i++) {
                    msg = new MensagemDaPlanta(TipoDeMensagem.PUMP_STATE);
                    msg.setConteudo(i, arrayBombas[i].isBombaAberta());
                    filaParaControle.post(msg);
                }
            }

            // Recebe mensagens
            while(!filaDoControle.isEmpty()) {
                MensagemDoControle msg = filaDoControle.read();
                int bomba = -1;

                switch(msg.tipo) {
                case OPEN_PUMP:
                    bomba = msg.getConteudo();
                    if (bomba >= 0 && bomba < 4) {
                        arrayBombas[bomba].abre();
                    }
                    break;
                case CLOSE_PUMP:
                    bomba = msg.getConteudo();
                    if (bomba >= 0 && bomba < 4) {
                        arrayBombas[bomba].fecha();
                    }
                    break;
                case OPEN_VALVE:
                    valvula.setAberta(true);
                    break;
                case CLOSE_VALVE:
                    valvula.setAberta(false);
                    break;
                case PROGRAM_READY:
                    MensagemDaPlanta msgP = new MensagemDaPlanta(TipoDeMensagem.PHYSICAL_UNITS_READY);
                    filaParaControle.post(msgP);
                    break;
                case MODE:
                    mode = msg.getConteudo();
                    if(mode == 2) {
                        sensorVapor.setFluxo(13);
                    }
                    if(mode == 3) {//MODO PARADA DE EMERGENCIA
                        botao.push();
                    }
                    break;
                default:
                        break;
                }
            }
            
            if(botao.getBotaoEmerg()) {
                caldeiraLigada = false;
            }

            // Proxima iteracao
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                // Tratar, embora nao cause maiores danos
            }
        }
    }
    
    
}
