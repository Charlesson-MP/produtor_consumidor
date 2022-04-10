import java.util.ArrayList;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

/* ***************************************************************
* Autor............: Charlesson Mendes Pereira
* Matricula........: 201710786
* Inicio...........: 06/04/2022
* Ultima alteracao.: 09/04/2022
* Nome.............: Produtor-consumidor
* Funcao...........: Definir as tarefas da classe Produtor
*************************************************************** */
public class Produtor extends Thread { //Inicio da classe Produtor
  //Declarando atributos
  private Buffer buffer;
  private ImageView imgFerreiro1;
  private ImageView imgFerreiro2;
  private ImageView imgEspada1;
  private ImageView imgEspada2;
  private ImageView imgMarchado1;
  private ImageView imgMarchado2;
  private ImageView imgEscudo1;
  private ImageView imgEscudo2;

  private ArrayList<Integer> valSorteados;

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
  public Produtor(Buffer buffer, ImageView imgFerreiro1, ImageView imgFerreiro2, ImageView imgEspada1,
  ImageView imgEspada2, ImageView imgMarchado1, ImageView imgMarchado2, ImageView imgEscudo1, ImageView imgEscudo2) { //Inicio do metodo Produtor
    this.buffer = buffer; //Atributo buffer recebe parametro buffer
    this.imgFerreiro1 = imgFerreiro1; //Atributo imgFerreiro1 recebe parametro imgFerreiro1
    this.imgFerreiro2 = imgFerreiro2; //Atributo imgFerreiro2 recebe parametro imgFerreiro2
    this.imgEspada1 = imgEspada1; //Atributo imgEspada1 recebe parametro imgEspada1
    this.imgEspada2 = imgEspada2; //Atributo imgEspada2 recebe parametro imgEspada2
    this.imgMarchado1 = imgMarchado1; //Atributo imgMarchado1 recebe parametro imgMarchado1
    this.imgMarchado2 = imgMarchado2; //Atributo imgMarchado2 recebe parametro imgMarchado2
    this.imgEscudo1 = imgEscudo1; //Atributo imgEscudo1 recebe parametro imgEscudo1
    this.imgEscudo2 = imgEscudo2; //Atributo imgEscudo2 recebe parametro imgEscudo2
    this.valSorteados = new ArrayList<Integer>(6);
  } //Fim do metodo Produtor

  public static boolean comparador(int val, ArrayList<Integer> n) {
    boolean chave = false;
    if(n.contains(val)) {
      chave = true;
    }
    return chave;
  }

  /* ***************************************************************
  * Metodo: geradorNum
  * Funcao: Gerar um numero aleatorio entre 1 e 6
  * Parametros: Sem parametros
  * Retorno: valor do tipo inteiro
  *************************************************************** */
  private int geradorNum(ArrayList<Integer> n) { //Inicio do metodo geradorNum
    Random aleatorio = new Random(); //Instancia da classe Random
    int valorGerado = aleatorio.nextInt(6) + 1; //Atribuindo o numero gerado a variavel valor
    int valorFinal = 0;
    if(n.size() == 0) {
      n.add(valorGerado);
      valorFinal = valorGerado;
    } else {
      if(comparador(valorGerado, n) == false) {
        n.add(valorGerado);
        valorFinal = valorGerado;
      }
    }

    if(valorFinal != 0) return valorFinal;
    else return geradorNum(n);
  } //Fim do metodo geradorNum

  /* ***************************************************************
  * Metodo: escolherItem
  * Funcao: Decidir qual item sera produzido a partir do valor retornado por geradorNum
  * Parametros: Sem parametros
  * Retorno: img do tipo ImageView
  *************************************************************** */
  private ImageView escolherItem() { //Inicio do metodo escolherItem
    ImageView img; //Declarando variavel do tipo imageView
    switch(geradorNum(this.valSorteados)) { //Inicio switch
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
  * Metodo: getVelProducao
  * Funcao: Retornar o valor do atributo velProducao
  * Parametros: 
  * Retorno: velProducao do tipo int
  *************************************************************** */
  public int getTempoProducao() { //Inicio do metodo getVelProducao
    return this.tempoProducao; //Retornando valor do atributo velProducao
  } //Fim do metodo getVelProducao

  /* ***************************************************************
  * Metodo: setVelProducao
  * Funcao: Setar o valor do atributo velProducao
  * Parametros: vel do tipo int
  * Retorno: Sem retorno
  *************************************************************** */
  public void setTempoProducao(int tempo) { //Inicio do metodo setVelProducao
    this.tempoProducao = tempo; //Retornando valor do atributo velProducao
  } //Fim do metodo setVelProducao

  /* ***************************************************************
  * Metodo: produzirItem
  * Funcao: simular a producao de itens para o estoque
  * Parametros: Sem parametros
  * Retorno: Sem retorno
  *************************************************************** */
  private void produzirItem()  {
    int cont = 0;
    while(cont < this.tempoProducao/1000) { //Inicio do while
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
      System.out.println(cont + " " + this.tempoProducao);
      cont++; //Incrementa em 1 o valor de count

      try { //Inicio do bloco try/catch
        sleep(1000); //Chamada ao metodo sleep
      } catch(InterruptedException e){ //Caso a excecao seja capturada
        e.printStackTrace(); //Mensagem imprimida na pilha
      } //Fim do bloco try/catch
    } //Fim do while

    if(imgFerreiro2.isVisible()) {
      imgFerreiro2.setVisible(false);
      imgFerreiro1.setVisible(true);
    }
  } //Fim do metodo produzirItem

  private void inserirItem(ImageView img) {    
    this.buffer.adicionar(img);
    img.setVisible(true);
  }

  /* ***************************************************************
  * Metodo: run
  * Funcao: Define o comportamento da Thread
  * Parametros: Sem parametros 
  * Retorno: Sem retorno
  *************************************************************** */
  public void run() { //Inicio do metodo run
    while(this.buffer.getTamanho() < 6) {
      produzirItem();
      inserirItem(escolherItem());
    }
  } //Fim do metodo run
} //Fim da classe Produtor
