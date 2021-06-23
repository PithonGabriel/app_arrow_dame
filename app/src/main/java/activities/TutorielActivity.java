package activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.apadnom.R;

import controller.GameBoard;
import controller.Pion;


public class TutorielActivity extends AppCompatActivity {

    private GameBoard game;
    private RelativeLayout boardLayout;
    private RelativeLayout possibilitiesLayout;
    private Pion[][] display_mat = new Pion[7][9];
    private int[] selected = new int[2];
    private int sx;
    private int sy;
    private Button btn;
    private TextView nb_jump_w;
    private int turn = 0;
    private TextView tutoriel;
    private Button suivant;
    private Button precedent;
    private Button quitter;
    private int index = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoriel);

        this.boardLayout = (RelativeLayout) findViewById(R.id.board);
        this.possibilitiesLayout = (RelativeLayout) findViewById(R.id.possibilites);
        game = new GameBoard("51o", getApplicationContext());

        nb_jump_w = (TextView) findViewById(R.id.nb_jump_w);
        tutoriel = (TextView) findViewById(R.id.tutoriel);

        tutoriel.setText("Voici le plateau de jeu !");

        precedent = (Button) findViewById(R.id.precedent);
        suivant = (Button) findViewById(R.id.suivant);
        quitter = (Button) findViewById(R.id.quitter);

        precedent.setVisibility(View.GONE);
        quitter.setVisibility(View.GONE);

        update();

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                turn = 0;
                bigSwitch();
                update();
            }
        });

        precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                turn = 0;
                bigSwitch();
                update();
            }
        });

        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                turn = 0;
                bigSwitch();
                update();
            }
        });

    }

    private void bigSwitch() {
        switch (index) {
            case (0):
                gameboardTuto();
                break;
            case (1):
                pionsTuto();
                break;
            case (2):
                arrowsTuto();
                break;
            case (3):
                starsTuto();
                break;
            case (4):
                goalTuto();
                break;
            case (5):
                moveArrow();
                break;
            case (6):
                jumpArrow();
                break;
            case (7):
                jumpLateralArrow();
                break;
            case (8):
                moveStar();
                break;
            case (9):
                jumpStar();
                break;
            case (10):
                shootingStar();
                break;
            case (11):
                mustJumpTuto();
                break;
            case (12):
                boardTuto();
                break;
            case (13):
                endTuto();
                break;
            case (14):
                closeTuto();
                break;
            default:
                index = 0;
        }
    }

    @SuppressLint("SetTextI18n")
    private void gameboardTuto() {
        precedent.setVisibility(View.GONE);
        quitter.setVisibility(View.GONE);
        tutoriel.setText("Voici le plateau de jeu !");
        game = new GameBoard("51o", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void pionsTuto() {
        precedent.setVisibility(View.VISIBLE);
        tutoriel.setText("Il existe deux types de pions");
        game = new GameBoard("51o", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void arrowsTuto() {
        tutoriel.setText("Ces pions sont appelés des flèches");
        game = new GameBoard("24o01b01o01d24o", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void starsTuto() {
        tutoriel.setText("Et ceux-là des étoiles");
        game = new GameBoard("24o01a01o01c24o", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void goalTuto() {
        tutoriel.setText("Le but du jeu est d'amener ses 3 étoiles de l'autre côté du plateau");
        game = new GameBoard("03c48o", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void moveArrow() {
        tutoriel.setText("Essaye de faire bouger la flèche en cliquant dessus !");
        game = new GameBoard("25o01d25o", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void jumpArrow() {
        tutoriel.setText("Si tu es bloqué par des pions adverses ou alliés sautez par dessus !");
        game = new GameBoard("16o01b01d06o01b01d01b24o", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void jumpLateralArrow() {
        tutoriel.setText("Tu peux même sauter plusieurs fois de suite avec la même flèche. Après un saut, tu peux aussi faire un saut sur le côté.");
        game = new GameBoard("09o01b06o01b01d06o01b01d01b24o", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void moveStar() {
        tutoriel.setText("Les étoiles bougent différemment, essayez en cliquant dessus !");
        game = new GameBoard("25o01c25o", getApplicationContext());
        game.setHas_jumped((byte) 1);
        game.setJump(2);
    }

    @SuppressLint("SetTextI18n")
    private void jumpStar() {
        tutoriel.setText("Tu peux aussi sauter avec les étoiles !");
        game = new GameBoard("16o02b06o01b01c01b24o", getApplicationContext());
        game.setHas_jumped((byte) 1);
        game.setJump(1);
    }

    @SuppressLint("SetTextI18n")
    private void shootingStar() {
        tutoriel.setText("Mais pour bouger vos étoiles il faut d'abord sauter par dessus un pion de l'adversaire avec une de vos flèches");
        game = new GameBoard("16o02b06o01b01d01b18o03c03o", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void mustJumpTuto() {
        tutoriel.setText("Si tu peux sauter avec une flèche, tu ne peux pas juste bouger avec une autre");
        game = new GameBoard("16o02b06o01b01d01b01o01d22o", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void boardTuto() {
        quitter.setVisibility(View.GONE);
        suivant.setVisibility(View.VISIBLE);
        tutoriel.setText("Voici le plateau au début d'une partie !");
        game = new GameBoard("03b03a07b25o07d03c03d", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void endTuto() {
        suivant.setVisibility(View.GONE);
        quitter.setVisibility(View.VISIBLE);
        tutoriel.setText("Maintenant que tu connais les règles tu peux essayer de jouer contre un ami !");
        game = new GameBoard("03b03a07b25o07d03c03d", getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    private void closeTuto() {
        finish();
    }

    public void setSelected(int a, int b) {
        this.selected[0] = a;
        this.selected[1] = b;
    }

    public int[] getSelected() {
        selected[0] = sx;

        if (sx == 0) {
            selected[1] = (sy + 7) % 9;
        } else if (selected[0] == 1) {
            selected[1] = (sy + 8) % 9;
        } else if (selected[0] == 2) {
            selected[1] = (sy + 8) % 9;
        } else if (selected[0] == 5) {
            selected[1] = (sy + 1) % 9;
        } else if (selected[0] == 6) {
            selected[1] = (sy + 1) % 9;
        } else {
            selected[1] = sy;
        }
        return selected;
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public void update() {
        System.out.println("turn:" + turn);
        // faudrait peut etre trouver autre chose
        //affichage du cadre

        this.display_mat = game.display();

        int y = 0;
        int x = 0;

        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 9; j++) {

                if (display_mat[i][j] == null) {
                    x += 100;
                    continue;
                }

                ImageView img = new ImageView(this);
                img.setImageDrawable(getDrawable(display_mat[i][j].getImg()));

                int finalI = i;
                int finalJ = j;

                if (display_mat[i][j].get_color() != -1) {
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            possibilitiesLayout.removeAllViews();
                            sx = finalI;
                            sy = finalJ;
                            setSelected(sx, sy);
                            if (game.check_selection(getSelected()[0], getSelected()[1], turn, 1) == 0) {
                                display_possibilities(getSelected()[0], getSelected()[1]);
                            }
                        }
                    });
                }

                if (display_mat[i][j].get_direction() == 0)
                    img.setRotation(270);
                else
                    img.setRotation(90);

                RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(100, 100);
                parms.setMargins(x - 50, y, 0, 0);
                parms.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                parms.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                img.setLayoutParams(parms);

                x += 100;

                boardLayout.addView(img);

                getSelected();
            }
            y += 100;
            x = 50 * ((i + 1) % 2);
        }
        if ((turn % 2) == 0) {
            nb_jump_w.setText(String.valueOf(game.getJump()));
        } else if ((turn % 2) == 1) {
            nb_jump_w.setText("0");
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void display_possibilities(int px, int py) {
        if (game.getGameboard()[px][py] != null && game.getGameboard()[px][py].get_color() != -1) {
            int[][] pos = null;
            if (game.check_selection(px, py, turn, 1) != -1) {
                pos = game.get_possibilities(game.getCell(px, py), px, py);
            }
            if (pos != null) {
                for (int[] p : pos
                ) {
                    int[] tmp = getrelative_position(p);
                    int y = 0;
                    int x = 0;

                    for (int i = 0; i < 7; i++) {

                        for (int j = 0; j < 9; j++) {
                            if (i == tmp[0] && j == tmp[1]) {
                                ImageView img = new ImageView(this);
                                img.setImageDrawable(getDrawable(R.drawable.yellow_haxagone));
                                RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(100, 100);
                                int finalI = p[0];
                                int finalJ = p[1];
                                img.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        boardLayout.removeAllViews();
                                        setSelected(finalI, finalJ);
                                        System.out.println("move depuis:" + getSelected()[0] + getSelected()[1] + "vers :" + finalI + finalJ);
                                        game.move(finalI, finalJ);
                                        update();
                                        if (game.checkEndTurn()) {
                                            System.out.println("fin de tour");
                                            turn++;
                                            update();
                                            System.out.println("white : " + game.getNb_B_stars() + " black " + game.getNb_W_stars());
                                            game.end_turn();
                                            game.add_turn();
                                            System.out.println("zobturn" + turn);
                                            /*
                                            if(game.end_turn()==(byte)1) {
                                                System.out.println("Test");
                                                game.initGameBoard();
                                            }
                                            */

                                        }
                                        update();
                                    }
                                });
                                parms.setMargins(x -50, y, 0, 0);
                                parms.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                                parms.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                                img.setLayoutParams(parms);

                                possibilitiesLayout.addView(img);
                            }
                            x += 100;
                        }
                        y += 100;
                        x = 50 * ((i+1) % 2);
                    }
                }
            }
        }
    }

    public int[] getrelative_position(int[] pos) {
        int[] new_pos = new int[2];
        new_pos[0] = pos[0];

        if (new_pos[0] == 0) {
            new_pos[1] = (pos[1] + 2) % 9;
        } else if (new_pos[0] == 1) {
            new_pos[1] = (pos[1] + 1) % 9;
        } else if (new_pos[0] == 2) {
            new_pos[1] = (pos[1] + 1) % 9;
        } else if (new_pos[0] == 5) {
            new_pos[1] = (pos[1] - 1) % 9;
        } else if (new_pos[0] == 6) {
            new_pos[1] = (pos[1] + 8) % 9;
        } else {
            new_pos[1] = pos[1];
        }
        return new_pos;
    }


}