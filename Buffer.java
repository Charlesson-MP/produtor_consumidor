import java.util.ArrayList;

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
  //Fim da declaracao de atributos

  /* ***************************************************************
  * Metodo: Buffer
  * Funcao: Dificir o estado inicioal de uma instancia da classe
  * Parametros: Sem parametros
  * Retorno: Sem retorno
  *************************************************************** */
  public Buffer() { //Inicio do metodo Buffer
    buffer = new ArrayList<ImageView>(6); //Instanciando atributo buffer
  } //Fim do metodo Buffer

  /* ***************************************************************
  * Metodo: adicionar
  * Funcao: Adicionar um elemento no buffer
  * Parametros: img da classe ImageView
  * Retorno: Sem retorno
  *************************************************************** */
  public void adicionar(ImageView img) { //Inicio do metodo adicionar
    buffer.add(img); //Adiciona um elemento no buffer
  } //Fim do metodo adicionar

  /* ***************************************************************
  * Metodo: remover
  * Funcao: Remover elemento do buffer
  * Parametros: num do tipo int
  * Retorno: Sem retorno
  *************************************************************** */
  public void remover(int num) { //Inicio do metodo remover
    buffer.remove(num); //Remove um elemento do buffer
  } //Fim do metodo remover

  public ImageView getItem(int num) {
    return buffer.get(num);
  }

  public int getTamanho() {
    return this.buffer.size();
  }

} //Fim da classe Buffer
