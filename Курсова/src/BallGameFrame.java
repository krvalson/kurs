//package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class BallGameFrame extends JFrame
{
    public final int BASE_RADIUS=5; //Начальный радиус шариков
    public final int START_QNTBALLS=10; //Количество шариков на первом уровне

    private int level=1; //Первый уровень
    private int ballQnt;
    private BallComponent ballComponent;
    private MousePlayer mousePlayerListener;

    //конструктор
    public BallGameFrame() {
        ballQnt=START_QNTBALLS;
        setTitle("BallGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ballComponent = new BallComponent();
        ballComponent.setBackground(Color.DARK_GRAY);
        mousePlayerListener = new MousePlayer();
        add(ballComponent, BorderLayout.CENTER);
        final JPanel buttonPanel = new JPanel();
        final JButton startButton = new JButton("Начать игру.");
        buttonPanel.add(startButton);
        final JLabel scoreLabel = new JLabel();
        buttonPanel.add(scoreLabel);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ballComponent.addMouseListener(mousePlayerListener);
                ballComponent.addMouseMotionListener(mousePlayerListener);
                startButton.setVisible(false);
                ballComponent.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                startGame(scoreLabel, ballQnt);
            }});
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }
    public void startGame(JLabel scoreLabel, int ballQnt){
        Runnable r = new BallRunnable(ballComponent, scoreLabel, level, ballQnt);
        Thread t = new Thread(r);
        t.start();
    }
    // внутренний Класс MousePlayer, для отработки событий от мыши:
    class MousePlayer extends MouseAdapter{
        public void mouseClicked(MouseEvent e) { //Создаем шарик игрока
            Random random = new Random();
            //Создаем шарик игрока, с приращением радиуса равным единице
            //и приращением координат (скоростями), равными нулю
            Ball ball = new Ball(e.getX(),
                    e.getY(),
                    0,
                    0,
                    BASE_RADIUS,
                    new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)),
                    1,
                    1);
            ballComponent.startClick=true;
            ballComponent.addBall(ball);
            //Удаляем слушателя мыши, чтобы пользователь не мог накликать еще шариков, и приводим курсор мыши в первоначальное положение
            ballComponent.removeMouseListener(mousePlayerListener);
            ballComponent.removeMouseMotionListener(mousePlayerListener);
            ballComponent.setCursor(Cursor.getDefaultCursor());
        }}}
