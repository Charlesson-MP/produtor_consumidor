/* ***************************************************************
* Autor............: Charlesson Mendes Pereira
* Matricula........: 201710786
* Inicio...........: 06/04/2022
* Ultima alteracao.: 10/04/2022
* Nome.............: Produtor-consumidor
* Funcao...........: Definir as tarefas da classe Produtor
*************************************************************** */
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Produtor extends Thread { //Inicio da classe Produtor
  //Declarando atributos
  private Group root;
  private Buffer buffer;
  private ImageView imgFerreiro1;
  private ImageView imgFerreiro2;
  private File fileImgEspada1;
  private File fileImgEspada2;
  private File fileImgMarchado1;
  private File fileImgMarchado2;
  private File fileImgEscudo1;
  private File fileImgEscudo2;
  private Semaphore mutex;
  private Semaphore empty;
  private Semaphore full;

  private int tempoProducao;
  //Fim da declaracao de atributos

  /* ***************************************************************
  * Metodo: Produtor
  * Funcao: Definir o estado inicial de uma instancia da classe Produtor
  * Parametros: buffer como ArrayList de ImageView e imgFerreiro1, imgFerreiro2,
  * imgEspada1, imgEspada2, imgMarchado1, imgMarchado2, imgEscudo1, imgEscudo2
  * da classe ImageView
  * Retorno: Sem retorno
  *************************************************************** */
  public Produtor(Group root, Buffer buffer, ImageView imgFerreiro1, ImageView imgFerreiro2, File fileImgEspada1,
  File fileImgEspada2, File fileImgMarchado1, File fileImgMarchado2, File fileImgEscudo1, File fileImgEscudo2,
  Semaphore mutex, Semaphore empty, Semaphore full) { //Inicio do metodo Produtor
    this.root = root;
    this.buffer = buffer; //Atributo buffer recebe parametro buffer
    this.imgFerreiro1 = imgFerreiro1; //Atributo imgFerreiro1 recebe parametro imgFerreiro1
    this.imgFerreiro2 = imgFerreiro2; //Atributo imgFerreiro2 recebe parametro imgFerreiro2
    this.fileImgEspada1 = fileImgEspada1; //Atributo imgEspada1 recebe parametro imgEspada1
    this.fileImgEspada2 = fileImgEspada2; //Atributo imgEspada2 recebe parametro imgEspada2
    this.fileImgMarchado1 = fileImgMarchado1; //Atributo imgMarchado1 recebe parametro imgMarchado1
    this.fileImgMarchado2 = fileImgMarchado2; //Atributo imgMarchado2 recebe parametro imgMarchado2
    this.fileImgEscudo1 = fileImgEscudo1; //Atributo imgEscudo1 recebe parametro imgEscudo1
    this.fileImgEscudo2 = fileImgEscudo2; //Atributo imgEscudo2 recebe parametro imgEscudo2
    this.tempoProducao = 1;
    this.mutex = mutex;
    this.empty = empty;
    this.full = full;
  } //Fim do metodo Produtor



  /* ***************************************************************
  * Metodo: escolherItem
  * Funcao: Decidir qual item sera produzido a partir do valor retornado por geradorNum
  * Parametros: Sem parametros
  * Retorno: img do tipo ImageView
  *************************************************************** */
  private ImageView escolherItem() { //Inicio do metodo escolherItem
    ImageView img; //Declarando variavel do tipo imageView
    switch(buffer.geradorNum(buffer.getListValSorteados())) { //Inicio switch
      case 1: //Caso geradorNum retorne 1
        img = new ImageView(this.fileImgEspada1.getPath()); //Variavel img recebe imgEspada1
        img.setX(360);
        img.setY(130);
        break; //Parada no switch
      case 2: //Caso geradorNum retorne 2
        img = new ImageView(this.fileImgEspada2.getPath()); //Variavel img recebe imgEspada2
        img.setX(360);
        img.setY(400);
        break; //Parada no switch
      case 3: //Caso geradorNum retorne 3
        img = new ImageView(this.fileImgMarchado1.getPath()); //Variavel img recebe imgMarchado1
        img.setX(590);
        img.setY(130);
        break; //Parada no switch
      case 4: //Caso geradorNum retorne 4
        img = new ImageView(this.fileImgMarchado2.getPath()); //Variavel img recebe imgMarchado2
        img.setX(570);
        img.setY(400);
        break; //Parada no switch
      case 5: //Caso geradorNum retorne 5
        img = new ImageView(this.fileImgEscudo1.getPath()); //Variavel img recebe imgEscudo1
        img.setX(800);
        img.setY(130);
        break; //Parada no switch
      case 6: //Caso geradorNum retorne 6
        img = new ImageView(this.fileImgEscudo2.getPath()); //Variavel img recebe imgEscudo2
        img.setX(800);
        img.setY(400);
        break; //Parada no switch
      default: //Caso geradorNum retorne outro valor
        img = null; //Variavel img recebe null
        break; //Parada no switch
    } //Fim do switch

    return img; //Retornando variavel img
  } //Fim do metodo escolher item

  /* ***************************************************************
  * Metodo: getTempoProducao
  * Funcao: Retornar o valor do atributo tempoProducao
  * Parametros: Sem parametros
  * Retorno: tempoProducao do tipo int
  *************************************************************** */
  public int getTempoProducao() { //Inicio do metodo getTempoProducao
    return this.tempoProducao; //Retornando valor do atributo tempoProducao
  } //Fim do metodo getTempoProducao

  /* ***************************************************************
  * Metodo: setTempoProducao
  * Funcao: Setar o valor do atributo tempoProducao
  * Parametros: vel do tipo int
  * Retorno: Sem retorno
  *************************************************************** */
  public void setTempoProducao(int tempo) { //Inicio do metodo setTempoProducao
    this.tempoProducao = tempo; //Atribuindo o valor do parametro tempo ao atributo tempoProducao
  } //Fim do metodo setTempoProducao

  /* ***************************************************************
  * Metodo: produzirItem
  * Funcao: simular a producao de itens para o estoque
  * Parametros: Sem parametros
  * Retorno: Sem retorno
  *************************************************************** */
  private void produzirItem()  {
    int cont = 0;
    while(cont < this.tempoProducao) { //Inicio do while
      if(imgFerreiro1.isVisible()) { //Inicio do if
        Platform.runLater(() -> {
          imgFerreiro1.setVisible(false); //Desabilita visibilidade do atributo imgFerreiro1
          imgFerreiro2.setVisible(true); //Habilita visibilidade do atributo imgFerreiro2
        });
      } else { //Inicio else
        Platform.runLater(() -> {
          imgFerreiro2.setVisible(false); //Desabilita visibilidade do atributo imgFerreiro2
          imgFerreiro1.setVisible(true); //Habilita visibilidade do atributo imgFerreiro1
        });
      } //Fim if/else

      try { //Inicio do bloco try/catch
        sleep(1000); //Chamada ao metodo sleep
      } catch(InterruptedException e){ //Caso a excecao seja capturada
        e.printStackTrace(); //Mensagem imprimida na pilha
      } //Fim do bloco try/catch

      cont++; //Incrementa em 1 o valor de count
    } //Fim do while

    if(imgFerreiro2.isVisible()) {
      imgFerreiro2.setVisible(false);
      imgFerreiro1.setVisible(true);
    }
  } //Fim do metodo produzirItem

  private void inserirItem(ImageView img) {    
    this.buffer.adicionarListImg(img);
    Platform.runLater(() -> {
      this.root.getChildren().add(img);
    });
  }

  /* ***************************************************************
  * Metodo: run
  * Funcao: Define o comportamento da Thread
  * Parametros: Sem parametros 
  * Retorno: Sem retorno
  *************************************************************** */
  public void run() { //Inicio do metodo run
    while(true) {
      if (this.empty.availablePermits() > 0) { //Inicio while
        produzirItem();
        try {
          empty.acquire();
          mutex.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        inserirItem(escolherItem());
        mutex.release();
        full.release();
        System.out.println(this.buffer.getListImg().size() + " " + this.buffer.getListValSorteados().size());
      } //Fim while
    }
  } //Fim do metodo run
} //Fim da classe Produtor
