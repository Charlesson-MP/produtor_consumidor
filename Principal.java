/* ***************************************************************
* Autor............: Charlesson Mendes Pereira
* Matricula........: 201710786
* Inicio...........: 06/04/2022
* Ultima alteracao.: 09/04/2022
* Nome.............: Principal
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
  private File fileImgCorrentes;
  private File fileImgEspada1;
  private File fileImgEspada2;
  private File fileImgMarchado1;
  private File fileImgMarchado2;
  private File fileImgEscudo1;
  private File fileImgEscudo2;

  private ImageView imgFundo;
  private ImageView imgFerreiro1;
  private ImageView imgFerreiro2;
  private ImageView imgGuerreiro1;
  private ImageView imgGuerreiro2;
  private ImageView imgGuerreiro3;
  private ImageView imgCorrentes;
  private ImageView imgEspada1;
  private ImageView imgEspada2;
  private ImageView imgMarchado1;
  private ImageView imgMarchado2;
  private ImageView imgEscudo1;
  private ImageView imgEscudo2;

  private Buffer buffer;

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
    root = new Group();

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
    imgGuerreiro1.setX(1000); //Setando a coordenada x do atributo imgGuerreiro1
    imgGuerreiro1.setY(400); //Setando a coordenada y do atributo imgGuerreiro1
    
    fileImgGuerreiro2 = new File("/imagens/guerreiro2.png"); //Instanciando atributo fileImgGuerreiro2
    imgGuerreiro2 = new ImageView(fileImgGuerreiro2.getPath()); //Instanciando o atributo imgGuerreiro2
    imgGuerreiro2.setX(1000); //Setando a coordenada x do atributo imgGuerreiro2
    imgGuerreiro2.setY(400); //Setando a coordenada y do atributo imgGuerreiro2

    fileImgGuerreiro3 = new File("/imagens/guerreiro3.png"); //Instanciando atributo fileImgGuerreiro2
    imgGuerreiro3 = new ImageView(fileImgGuerreiro3.getPath()); //Instanciando atributo imgGuerreiro3
    imgGuerreiro3.setX(1000); //Setando a coordenada x do atributo imgGuerreiro3
    imgGuerreiro3.setY(400); //Setando a coordenada y do atributo imgGuerreiro3

    fileImgCorrentes = new File("/imagens/correntes.png"); //Instancianto atributo fileImgCorrentes
    imgCorrentes = new ImageView(fileImgCorrentes.getPath()); //Instanciando atributo imgCorrentes
    imgCorrentes.setX(350); //Setando a coordenada x do atributo imgCorrentes
    imgCorrentes.setY(100);

    fileImgEspada1 = new File("/imagens/espada1.png"); //Instanciando atributo fileImgEspada1
    imgEspada1 = new ImageView(fileImgEspada1.getPath()); //Instanciando atributo imgEspada1
    imgEspada1.setVisible(false);
    imgEspada1.setX(380); //Setando a coordenada x do atributo imgEspada1
    imgEspada1.setY(130); //Setando a coordenada y do atributo imgEspada1

    fileImgEspada2 = new File("/imagens/espada2.png"); //Instanciando atributo fileImgEspada2
    imgEspada2 = new ImageView(fileImgEspada2.getPath()); //Instanciando atributo imgEspada2
    imgEspada2.setVisible(false);
    imgEspada2.setX(380); //Setando coordenada x do atributo imgEspada2
    imgEspada2.setY(400); //Setando coordenada y do atributo imgEspada2

    fileImgMarchado1 = new File("/imagens/marchado1.png"); //Instanciando atributo fileImgMarchado1
    imgMarchado1 = new ImageView(fileImgMarchado1.getPath()); //Instanciando atributo imgMarchado1
    imgMarchado1.setVisible(false);
    imgMarchado1.setX(590); //Setando coordenada x do atributo imgMarchado1
    imgMarchado1.setY(130); //Setando coordenada y do atributo imgMarchado1

    fileImgMarchado2 = new File("/imagens/marchado2.png"); //Instanciando atributo fileImgMarchado2
    imgMarchado2 = new ImageView(fileImgMarchado2.getPath()); //Instanciando atributo imgMarchado2
    imgMarchado2.setVisible(false);
    imgMarchado2.setX(590); //Setando coordenada x do atributo imgMarchado2
    imgMarchado2.setY(400); //Setando coordenada y do atributo imgMarchado2

    fileImgEscudo1 = new File("/imagens/escudo1.png"); //Instanciando atributo fileImgEscudo1
    imgEscudo1 = new ImageView(fileImgEscudo1.getPath()); //Instanciando atributo imgEscuto1
    imgEscudo1.setVisible(false);
    imgEscudo1.setX(820); //Setando a coordenada x do atributo imgEscudo1
    imgEscudo1.setY(130); //Setando a coordenady x do atributo imgEscudo1

    fileImgEscudo2 = new File("/imagens/escudo2.png"); //Instanciando atributo fileImgEscudo2
    imgEscudo2 = new ImageView(fileImgEscudo2.getPath()); //Instanciando atributo imgEscuto2
    imgEscudo2.setVisible(false);
    imgEscudo2.setX(820); //Setando a coordenada x do atributo imgEscudo2
    imgEscudo2.setY(400); //Setando a coordenady x do atributo imgEscudo2

    buffer = new Buffer();

    Produtor produtor = new Produtor(buffer, imgFerreiro1, imgFerreiro2, imgEspada1, imgEspada2, imgMarchado1,
    imgMarchado2, imgEscudo1, imgEscudo2, root);
    produtor.setTempoProducao(2000);
    

    btn = new Button("Apertar");
    btn.setLayoutX(300);
    btn.setLayoutY(100);
    btn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          produtor.start();
        }
    });

    root.getChildren().addAll(imgFundo, imgFerreiro1, imgFerreiro2, btn, imgGuerreiro1, imgGuerreiro2, imgGuerreiro3,
    imgCorrentes, imgEspada1, imgEspada2, imgMarchado1, imgMarchado2, imgEscudo1, imgEscudo2); //Instanciando atributo root
    scene = new Scene(root, 1200, 690, Color.BLACK); //Instanciando atributo scene
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