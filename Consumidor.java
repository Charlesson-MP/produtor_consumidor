import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

/* ***************************************************************
* Autor............: Charlesson Mendes Pereira
* Matricula........: 201710786
* Inicio...........: 06/04/2022
* Ultima alteracao.: 10/04/2022
* Nome.............: Consumidor
* Funcao...........: Definir as tarefas da classe Consumidor
*************************************************************** */
public class Consumidor extends Thread { //Inicio da classe consumidor
  //Declarando atributos
  private Buffer buffer;
  private ImageView imgGuerreiro;

  private Semaphore mutex;
  private Semaphore empty;
  private Semaphore full;


  private int tempoConsumo;
  //Fim da declaracao de atributos

  /* ***************************************************************
  * Metodo: Consumidor
  * Funcao: Definir o estado inicial de uma instancia da classe Consumidor
  * Parametros: buffer como ArrayList de ImageView e imgGuerreiro, imgEspada1,
  * imgEspada2, imgMarchado1, imgMarchado2, imgEscudo1, imgEscudo2
  * da classe ImageView
  * Retorno: Sem retorno
  *************************************************************** */
  public Consumidor(Buffer buffer, ImageView imgGuerreiro, Semaphore mutex, Semaphore empty, Semaphore full) { //Inicio do metodo Consumidor
    this.buffer = buffer;
    this.imgGuerreiro = imgGuerreiro;
    this.tempoConsumo = 1;
    this.mutex = mutex;
    this.empty = empty;
    this.full = full;
  } //Fim do metodo Consumidor
  
  /* ***************************************************************
  * Metodo: escolherItem
  * Funcao: Decidir qual item sera produzido a partir do valor retornado por geradorNum
  * Parametros: Sem parametros
  * Retorno: img do tipo ImageView
  *************************************************************** */
  private ImageView escolherItem(Buffer lista) { //Inicio do metodo escolherItem
    int count = 0;
    ImageView img = lista.getItem(1);
    
    while(count < this.tempoConsumo) {
      try {
        sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      count++;
    }

    return img; //Retornando variavel img
  } //Fim do metodo escolher item

  /* ***************************************************************
  * Metodo: getTempoConsumo
  * Funcao: Retornar o valor do atributo tempoConsumo
  * Parametros: Sem parametros
  * Retorno: tempoConsumo do tipo int
  *************************************************************** */
  public int getTempoConsumo() { //Inicio do metodo getTempoConsumo
    return this.tempoConsumo; //Retornando valor do atributo tempoConsumo
  } //Fim do metodo getTempoConsumo

  /* ***************************************************************
  * Metodo: setTempoProducao
  * Funcao: Setar o valor do atributo tempoConsumo
  * Parametros: tempo do tipo int
  * Retorno: Sem retorno
  *************************************************************** */
  public void setTempoConsumo(int tempo) { //Inicio do metodo setTempoConsumo
    this.tempoConsumo = tempo; //Atribuindo o valor do parametro tempo ao atributo tempoConsumo
  } //Fim do metodo setTempoConsumo

  /* ***************************************************************
  * Metodo: removerItem
  * Funcao: Remover item do buffer
  * Parametros: tempo do tipo int
  * Retorno: Sem retorno
  *************************************************************** */
  public ImageView removerItem(Buffer buffer) { //Inicio do metodo removerItem
    
    ImageView img = escolherItem(buffer);
    int num = buffer.getListImg().indexOf(img);

    Platform.runLater(() -> {
      buffer.getListValSorteados().remove(buffer.getListValSorteados().indexOf(num));
      buffer.removerListImg(num);
    });
    return img;
  } //Fim do metodo removerItem

  public void entrarNaLoja(ImageView img) {
    while(img.getX() > 650) {
      Platform.runLater(() -> {
          img.setX(img.getX() - 1);
      });
      
      try { //Inicio do bloco try/catch
        sleep(10); //Chamada ao metodo sleep
      } catch(InterruptedException e){ //Caso a excecao seja capturada
        e.printStackTrace(); //Mensagem imprimida na pilha
      } //Fim do bloco try/catch
    }
  }

  public ImageView pegarItem(ImageView img) {
    Platform.runLater(() -> {
      img.setX(630);
      img.setY(500);
    });
    
    return img;
  }

  public void sairDaLoja(ImageView img, ImageView imgArma) {
    while(img.getX() < 1250) {
      Platform.runLater(() -> {
        img.setX(img.getX() + 1);
        imgArma.setX(imgArma.getX() + 1);
      });
      try { //Inicio do bloco try/catch
        sleep(10); //Chamada ao metodo sleep
      } catch(InterruptedException e){ //Caso a excecao seja capturada
        e.printStackTrace(); //Mensagem imprimida na pilha
      } //Fim do bloco try/catch
    }
  }
  
  /* ***************************************************************
  * Metodo: run
  * Funcao: Define o comportamento da Thread
  * Parametros: Sem parametros 
  * Retorno: Sem retorno
  *************************************************************** */
  public void run() { //Inicio do metodo run
    while(true) {
      if(full.availablePermits() > 0) {
        entrarNaLoja(this.imgGuerreiro);
        try {
          full.acquire();
          mutex.acquire();
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }     
        mutex.release();
        empty.release();
        
        sairDaLoja(this.imgGuerreiro, pegarItem(removerItem(this.buffer)));
      }
    }
  } //Fim do metodo run

  
} //Fim da classe consumidor