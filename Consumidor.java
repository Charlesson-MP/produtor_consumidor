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
  private ImageView imgEspada1;
  private ImageView imgEspada2;
  private ImageView imgMarchado1;
  private ImageView imgMarchado2;
  private ImageView imgEscudo1;
  private ImageView imgEscudo2;

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
  public Consumidor(Buffer buffer, ImageView imgGuerreiro, ImageView imgEspada1, ImageView imgEspada2, ImageView imgMarchado1,
  ImageView imgMarchado2, ImageView imgEscudo1, ImageView imgEscudo2, Semaphore mutex, Semaphore empty, Semaphore full) { //Inicio do metodo Consumidor
    this.buffer = buffer;
    this.imgGuerreiro = imgGuerreiro;
    this.imgEspada1 = imgEspada1;
    this.imgEspada2 = imgEspada2;
    this.imgMarchado1 = imgMarchado1;
    this.imgMarchado2 = imgMarchado2;
    this.imgEscudo1 = imgEscudo1;
    this.imgEscudo2 = imgEscudo2;
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
  private ImageView escolherItem(int n) { //Inicio do metodo escolherItem
    int count = 0;
    while(count < this.tempoConsumo) {
      try {
        sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      count++;
    }
    ImageView img; //Declarando variavel do tipo imageView
    switch(n) { //Inicio switch
      case 1: //Caso geradorNum retorne 1
        img = this.imgEspada1; //Variavel img recebe imgEspada1
        break; //Parada no switch
      case 2: //Caso geradorNum retorne 2
        img = this.imgEspada2; //Variavel img recebe imgEspada2
        break; //Parada no switch
      case 3: //Caso geradorNum retorne 3
        img = this.imgMarchado1; //Variavel img recebe imgMarchado1
        break; //Parada no switch
      case 4: //Caso geradorNum retorne 4
        img = this.imgMarchado2; //Variavel img recebe imgMarchado2
        break; //Parada no switch
      case 5: //Caso geradorNum retorne 5
        img = this.imgEscudo1; //Variavel img recebe imgEscudo1
        break; //Parada no switch
      case 6: //Caso geradorNum retorne 6
        img = this.imgEscudo2; //Variavel img recebe imgEscudo2
        break; //Parada no switch
      default: //Caso geradorNum retorne outro valor
        img = null; //Variavel img recebe null
        break; //Parada no switch
    } //Fim do switch

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
    int num = buffer.geradorNum2(buffer.getListValSorteados());
    System.out.println(num);
    ImageView img = escolherItem(num);
    Platform.runLater(() -> {
      buffer.getListValSorteados().remove(buffer.getListValSorteados().indexOf((Integer) num));
      img.setVisible(false);
      buffer.removerListImg(buffer.getListImg().indexOf(img));
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
      img.setVisible(true);
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