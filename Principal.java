/* ***************************************************************
* Autor............: Charlesson Mendes Pereira
* Matricula........: 201710786
* Inicio...........: 06/04/2022
* Ultima alteracao.: 08/04/2022
* Nome.............: Produtor-consumidor
* Funcao...........: Resolver a condicao de corrida do problema
*                    sem espera ocupada, utilizando semaforo
*************************************************************** */

//Importacao de classes
import java.io.File;

import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
//Fim da importacao de classes


public class Principal extends Application { //Inicio da classe principal
  //Declaracao de atributos
  private Scene scene;
  private Group root;

  private File fileImgFundo;
  private File fileImgFerreiro1;
  private File fileImgFerreiro2;
  private File fileImgGuerreiro1;
  private File fileImgGuerreiro2;
  private File fileImgGuerreiro3;

  private ImageView imgFundo;
  private ImageView imgFerreiro1;
  private ImageView imgFerreiro2;
  private ImageView imgGuerreiro1;
  private ImageView imgGuerreiro2;
  private ImageView imgGuerreiro3;

  private Button btn;
  //Fim da declaracao de atributos

  /* ***************************************************************
  * Metodo: start
  * Funcao: Ponto de partida de aplicacoes javafx
  * Parametros: stage da classe Stage
  * Retorno: Sem retorno
  *************************************************************** */
  @Override
  public void start(Stage stage) throws Exception { //Inicio do metodo start
    inicializar(); //Chamando o metodo inicializar
    stage.setTitle("Produtor-consumidor"); //Setando o titulo da janela
    stage.setResizable(false); //Setando a propriedade redimensionavel
    stage.setScene(scene); //Setando a scene do stage
    stage.show(); //Mostrando a janela
  } //Fim do metodo start

  /* ***************************************************************
  * Metodo: inicializar
  * Funcao: Inicializar os atributos
  * Parametros: Sem parametros
  * Retorno: Sem retorno
  *************************************************************** */
  private void inicializar() { //Inicio do metodo inicializar
    

    fileImgFundo = new File("/imagens/fundo.jpg"); //Instanciando atributo fileImgFundo

    imgFundo = new ImageView(fileImgFundo.getPath()); //Instanciando atributo imgFundo
    imgFundo.setPreserveRatio(true); //Habilitando propriedade de proporcao do atributo imgFundo
    imgFundo.setSmooth(true); //Habilitando a propriedade smooth
    imgFundo.setFitWidth(930); //Setando a largura do atributo imgFundo
    imgFundo.setX(35); //Setando a coordenada x do atributo imgFundo

    fileImgFerreiro1 = new File("/imagens/ferreiro1.png");
    imgFerreiro1 = new ImageView(fileImgFerreiro1.getPath()); //Instanciando imgFerreiro
    imgFerreiro1.setY(330); //Setando a coordenada y do atributo imgFerreiro1

    fileImgFerreiro2 = new File("/imagens/ferreiro2.png"); //Instanciando atributo fileImgFerreiro2
    imgFerreiro2 = new ImageView(fileImgFerreiro2.getPath()); //Instanciando atributo imgFerreiro2
    imgFerreiro2.setY(255); //Setando a coordenada y do atributo imgFerreiro2
    imgFerreiro2.setVisible(false); //Desabilitando a visibilidade do atributo imgFerreiro2

    fileImgGuerreiro1 = new File("/imagens/guerreiro1.png"); //Instanciando atributo fileImgGuerreiro1
    imgGuerreiro1 = new ImageView(fileImgGuerreiro1.getPath()); //Instanciando atributo imgGuerreiro1
    imgGuerreiro1.setX(800);
    imgGuerreiro1.setY(400);
    
    fileImgGuerreiro2 = new File("/imagens/guerreiro2.png"); //Instanciando atributo fileImgGuerreiro2
    imgGuerreiro2 = new ImageView(fileImgGuerreiro2.getPath());
    imgGuerreiro2.setX(600);
    imgGuerreiro2.setY(400);

    fileImgGuerreiro3 = new File("/imagens/guerreiro3.png"); //Instanciando atributo fileImgGuerreiro2
    imgGuerreiro3 = new ImageView(fileImgGuerreiro3.getPath());
    imgGuerreiro3.setX(400);
    imgGuerreiro3.setY(400);

    btn = new Button("Apertar");
    btn.setLayoutX(300);
    btn.setLayoutY(100);
    btn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          if(imgFerreiro1.isVisible()) {
            imgFerreiro1.setVisible(false);
            imgFerreiro2.setVisible(true);
          } else {
            imgFerreiro2.setVisible(false);
            imgFerreiro1.setVisible(true);
          }
        }
    });


    root = new Group(imgFundo, imgFerreiro1, imgFerreiro2, btn, imgGuerreiro1, imgGuerreiro2, imgGuerreiro3); //Instanciando atributo root
    scene = new Scene(root, 1000, 690, Color.BLACK); //Instanciando atributo scene
  } //Fim do metodo inicializar

  /* ***************************************************************
  * Metodo: main
  * Funcao: Ponto de partida de programas java
  * Parametros: Vetor de Strings
  * Retorno: Sem retorno
  *************************************************************** */
  public static void main(String[] args) { //Inicio do metodo main
    launch(args); //Chamando metodo launch
  } //Fim do metodo main
} //Fim da classe principal