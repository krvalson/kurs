//package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class BallGame
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пользователя: ");
        String playerName = scanner.nextLine();

        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame ballFrame = new BallGameFrame();
                ballFrame.setVisible(true);
            }
        });
    }
}
