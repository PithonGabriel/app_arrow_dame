package com.example.apadnom;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apadnom.controller.GameBoard;

import controller.Pion;


public class MainActivity extends AppCompatActivity {

    private GameBoard game;
    private AbsoluteLayout myLayout;
    private AbsoluteLayout layout;
    private Pion[][] display_mat = new Pion[7][9];
    private int[] selected = new int[2];
    private int sx;
    private int sy;
    private int turn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.myLayout = (AbsoluteLayout) findViewById(R.id.layout);
        this.layout = (AbsoluteLayout) findViewById(R.id.head);
        game = new GameBoard();

        Button btn = new Button(this);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.initGameBoard();
                //myLayout.removeAllViews();
                update();
            }
        });
        Button btn1 = new Button(this);
        btn1 = (Button) findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.end_turn();
                turn ++;
                //myLayout.removeAllViews();
                update();
            }
        });

        update();

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

    public void update() {
        // faudrait peut etre trouver autre chose
        removeImages(myLayout);
        removeImages(myLayout);
        removeImages(myLayout);
        removeImages(myLayout);
        removeImages(myLayout);
        removeImages(myLayout);
        removeImages(myLayout);

        this.display_mat = game.display();
        int y = 0;
        int x = 0;
        for (int i = 0; i < 7; i++) {
            y += 100;
            x = 50 * (i % 2);
            for (int j = 0; j < 9; j++) {
                if (display_mat[i][j] == null) {
                    x += 100;
                    continue;
                }
                ImageView img = new ImageView(this);
                img.setImageDrawable(getDrawable(display_mat[i][j].getImg()));
                AbsoluteLayout.LayoutParams parms = new AbsoluteLayout.LayoutParams(100, 100, x, y);
                int finalI = i;
                int finalJ = j;
                if(display_mat[i][j].get_color() != -1) {
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sx = finalI;
                            sy = finalJ;
                            if(game.getHas_jumped() == 0) {
                                turn++;
                                game.end_turn();
                            }
                            setSelected(sx, sy);
                            removeImages(layout);
                            display_possibilities(getSelected()[0], getSelected()[1]);
                        }
                    });
                }
                if (display_mat[i][j].get_direction() == 0)
                    img.setRotation(270);
                else
                    img.setRotation(90);
                img.setLayoutParams(parms);

                x += 100;

                myLayout.addView(img);

                getSelected();
                String s = sx + ":" + selected[1];
                TextView t = (TextView) findViewById(R.id.selected);
                t.setText(s);
            }
        }
    }

    public void display_possibilities(int px, int py) {
        layout.removeAllViews();
        if (game.getGameboard()[px][py] != null && game.getGameboard()[px][py].get_color() != -1) {
            int[][] pos = null;
            if(game.check_selection(px, py, turn, 1) != -1) {
                pos = game.get_possibilities(game.getCell(px, py), px, py);
            }
            if(pos != null) {
                for (int[] p : pos
                ) {
                    System.out.println("possibilies: " + p[0] + p[1]);
                    int[] tmp = getrelative_position(p);
                    int y = 0;
                    int x = 0;

                    for (int i = 0; i < 7; i++) {
                        y += 100;
                        x = 50 * (i % 2);
                        for (int j = 0; j < 9; j++) {
                            if (i == tmp[0] && j == tmp[1]) {
                                ImageView img = new ImageView(this);
                                img.setImageDrawable(getDrawable(R.drawable.yellow_haxagone));
                                AbsoluteLayout.LayoutParams parms = new AbsoluteLayout.LayoutParams(100, 100, x, y);
                                img.setLayoutParams(parms);
                                int finalI = p[0];
                                int finalJ = p[1];
                                img.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        setSelected(finalI, finalJ);
                                        game.move(finalI, finalJ);
                                        layout.removeAllViews();
                                        removeImages(myLayout);
                                        removeImages(myLayout);
                                        update();
                                        if(game.getHas_jumped() == 0) {
                                            turn++;
                                            game.end_turn();
                                        }

                                    }
                                });
                                layout.addView(img);
                            }
                            x += 100;
                        }
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

    public void removeImages(AbsoluteLayout layout){
        for (int pos = 0; pos < layout.getChildCount(); pos++) {
            if (layout.getChildAt(pos) instanceof ImageView) {
                layout.removeView(layout.getChildAt(pos));
            }
        }
    }


}