import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.ImageView;

/* ***************************************************************
* Autor............: Charlesson Mendes Pereira
* Matricula........: 201710786
* Inicio...........: 06/04/2022
* Ultima alteracao.: 09/04/2022
* Nome.............: Buffer
* Funcao...........: Armazenar os produtos da classe Produtor
*************************************************************** */
public class Buffer { //Inicio da classe Buffer
  //Declaracao de atributos
  private ArrayList<ImageView> buffer;
  private ArrayList<Integer> valSorteados;
  private int numItens;
  //Fim da declaracao de atributos

  /* ***************************************************************
  * Metodo: Buffer
  * Funcao: Dificir o estado inicioal de uma instancia da classe
  * Parametros: Sem parametros
  * Retorno: Sem retorno
  *************************************************************** */
  public Buffer() { //Inicio do metodo Buffer
    this.buffer = new ArrayList<ImageView>(6); //Instanciando atributo buffer
    this.valSorteados = new ArrayList<Integer>(6);
    this.numItens = 0;
  } //Fim do metodo Buffer

  /* ***************************************************************
  * Metodo: adicionar
  * Funcao: Adicionar um elemento no buffer
  * Parametros: img da classe ImageView
  * Retorno: Sem retorno
  *************************************************************** */
  public void adicionarListImg(ImageView img) { //Inicio do metodo adicionar
    this.buffer.add(img); //Adiciona um elemento no buffer
    this.numItens++;
  } //Fim do metodo adicionar

  /* ***************************************************************
  * Metodo: remover
  * Funcao: Remover elemento do buffer
  * Parametros: num do tipo int
  * Retorno: Sem retorno
  *************************************************************** */
  public void removerListImg(int num) { //Inicio do metodo remover
    this.buffer.remove(num); //Remove um elemento do buffer
    this.numItens--;
  } //Fim do metodo remover

  public ImageView getItem(int num) {
    return buffer.get(num);
  }

  public int getTamanho() {
    return this.buffer.size();
  }

  public ArrayList<ImageView> getListImg() {
    return this.buffer;
  }

  public ArrayList<Integer> getListValSorteados() {
    return this.valSorteados;
  }

  /* ***************************************************************
  * Metodo: comparador
  * Funcao: Descobrir se a lista de valores sorteados possui um determinado valor
  * Parametros: val do tipo int e n como ArrayList de valores Integer
  * Retorno: chave do tipo boolean
  *************************************************************** */
  public boolean comparador(int val, ArrayList<Integer> n) { //Inicio do metodo comparador
    boolean chave = false;
    if(n.contains(val)) {
      chave = true;
    }
    return chave;
  } //Fim do metodo comparador

   /* ***************************************************************
  * Metodo: geradorNum
  * Funcao: Gerar um numero aleatorio entre 1 e 6
  * Parametros: Sem parametros
  * Retorno: valor do tipo inteiro
  *************************************************************** */
  public int geradorNum(ArrayList<Integer> n) { //Inicio do metodo geradorNum
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

  public int geradorNum2(ArrayList<Integer> n) {
    Random aleatorio = new Random(); //Instancia da classe Random
    int valorGerado = aleatorio.nextInt(100) + 1; //Atribuindo o numero gerado a variavel valor
    int valorFinal = 0;
    if(comparador(valorGerado, n)) valorFinal = valorGerado;

    if(valorFinal == 0) return geradorNum2(n);
    else return valorFinal;
  }
} //Fim da classe Buffer
