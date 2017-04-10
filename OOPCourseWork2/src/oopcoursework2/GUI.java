/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oopcoursework2;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Manul Singhe 2014254
 */
public class GUI {

    int credit = 10;
    int bet = 0;
    boolean status;
    Reel R = new Reel();
    Reel R1 = new Reel();
    Reel R2 = new Reel();
    Reel R3 = new Reel();

    int instance1;
    int instance2;
    int instance3;

    int Win;
    int wonCR;
    int totalCR;
    int lostCR;
    int avg;
    int matches;
    int stats;
    int ongoing = 0;

    public void gui() {
        //add window area
        JFrame window = new JFrame("Slot Machine");

        window.setSize(1150, 400);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add panel area
        JPanel panel = new JPanel();
        panel.setLayout(null);
        //assigning panle to frame.
        window.add(panel);

        // add spin button
        JButton spinButton = new JButton("Spin");
        spinButton.setBounds(187, 150, 125, 50);
        //assigning button to panel
        panel.add(spinButton);

        // add credit button
        JButton addCoin = new JButton("Add Coin");
        addCoin.setBounds(0, 35, 85, 25);
        //assigning button to panel
        panel.add(addCoin);

        // add bet one button
        JButton betOne = new JButton("Bet One");
        betOne.setBounds(0, 70, 85, 25);
        //assigning button to panel
        panel.add(betOne);

        // add max bet button
        JButton betMax = new JButton("Bet Max");
        betMax.setBounds(0, 105, 85, 25);
        //assigning button to panel
        panel.add(betMax);

        // add reset button
        JButton reset = new JButton("Reset");
        reset.setBounds(0, 140, 85, 25);
        //assigning button to panel
        panel.add(reset);

        // credit area
        JLabel lblCredit = new JLabel("Credit Area: 10");
        lblCredit.setBounds(0, 0, 85, 25);
        //assigning label to panel
        panel.add(lblCredit);

        // bet area
        JLabel lblBet = new JLabel("00");
        lblBet.setBounds(205, 50, 90, 65);
        lblBet.setFont(lblBet.getFont().deriveFont(80.0f));
        //assigning label to panel
        panel.add(lblBet);

        // error messages
        JLabel lblError1 = new JLabel("Insufficeint Credit !");
        lblError1.setVisible(false);
        //setting label color
        lblError1.setForeground(Color.red);
        lblError1.setBounds(200, 200, 200, 25);
        //assigning label to panel
        panel.add(lblError1);

        //add coin button that adds one to the current coins
        addCoin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                credit++;
                lblCredit.setText("Credit Area: " + credit);
                lblError1.setVisible(false);//hiding error message if diaplyed due to not enough credit
            }
        });

        //bet one button that adds one to the current bet
        betOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //checking if there is enough coins to bet with
                if (credit > 0) {
                    bet++;
                    credit--;
                    lblCredit.setText("Credit Area: " + credit);
                    lblBet.setText("" + bet);

                } else {
                    lblError1.setVisible(true);
                }
            }
        });

        // the max bet button added for convinence
        betMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //checking if suffiecnt credit is there to bet with
                if (credit > 2) {
                    bet = bet + 3;
                    credit = credit - 3;
                    lblBet.setText("" + bet);
                    lblCredit.setText("Credit Area: " + credit);

                } else {
                    lblError1.setVisible(true);//diaplying error if not enough credits found
                }
            }
        });

        //action to reset the values.
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ongoing == 0) {

                    credit = credit + bet;
                    bet = 0;
                    lblBet.setText("00");
                    lblCredit.setText("Credit Area: " + credit);
                    status = false;
                    lblError1.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot reset while game is ongoing!");
                }
            }

        });

        //displaying images in the buttons
        ImageIcon img1 = new ImageIcon("Images\\redseven.png");
        JButton lbl2 = new JButton(img1);
        lbl2.setBounds(400, 0, 212, 212);
        panel.add(lbl2);

        ImageIcon img2 = new ImageIcon("Images\\bell.png");
        JButton lbl1 = new JButton(img2);
        lbl1.setBounds(620, 0, 212, 212);
        panel.add(lbl1);

        ImageIcon img3 = new ImageIcon("Images\\cherry.png");
        JButton lbl3 = new JButton(img3);
        lbl3.setBounds(840, 0, 212, 212);
        panel.add(lbl3);

        JLabel creditLost = new JLabel("Credit Lost: 0");
        creditLost.setBounds(395, 205, 300, 65);
        panel.add(creditLost);

        JLabel creditBetted = new JLabel("Credit Betted: 0");
        creditBetted.setBounds(395, 235, 300, 65);
        panel.add(creditBetted);

        JLabel avgCredit = new JLabel("Avg. per game: 0");
        avgCredit.setBounds(395, 265, 300, 65);
        panel.add(avgCredit);

        JButton statistics = new JButton("Statistics");
        statistics.setBounds(850, 235, 200, 50);
        panel.add(statistics);

        statistics.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (stats == 1) {
                    JFrame window1 = new JFrame("Statistics");
                    window1.setSize(300, 300);
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window1.setResizable(false);
                    JPanel panel1 = new JPanel();
                    window1.add(panel1);
                    panel1.setLayout(null);

                    avg = wonCR / matches;

                    JLabel totalWins = new JLabel("Total Wins : " + Win);
                    totalWins.setBounds(20, 0, 250, 25);
                    panel1.add(totalWins);

                    JLabel totalCreditEarned = new JLabel("Total Credits Gained : " + totalCR);
                    totalCreditEarned.setBounds(20, 30, 250, 25);
                    panel1.add(totalCreditEarned);

                    JLabel totalLost = new JLabel("Total Losses : " + lostCR);
                    totalLost.setBounds(20, 60, 250, 25);
                    panel1.add(totalLost);

                    JLabel averageCredits = new JLabel("Average per game: " + avg);
                    averageCredits.setBounds(20, 90, 250, 25);
                    panel1.add(averageCredits);

                    JButton save = new JButton("Save");
                    save.setBounds(20, 120, 250, 25);
                    panel1.add(save);

                    window1.setVisible(true);

                    save.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
                            Date dObj = new Date();
                            String date = df.format(dObj);
                            String filename = date + ".txt";

                          
                                File f = new File(System.getProperty("user.home") + "/Desktop");//gets to the users desktop
                                File f2 = new File(f, filename);//creates a text file on the desktop
                            try {
                                f2.createNewFile();//creates a new file
                            } catch (IOException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                try (BufferedWriter bw = new BufferedWriter(new FileWriter(f2))) {//uses buffered writer to write data line by line

                                    bw.write(date);
                                    bw.newLine();
                                    bw.write("\t Number of Matches  : " + matches);
                                    bw.newLine();
                                    bw.write("\t Matches Won        : " + Win);
                                    bw.newLine();

                                    bw.write("\t Credits Won        : " + wonCR);
                                    bw.newLine();
                                    bw.write("\t Credits Lost       : " + lostCR);
                                    bw.newLine();
                                    bw.write("\t Average            : " + avg);
                                    bw.flush();
                                    bw.close();
                                    File filedesktop = new File(f, filename);
                                    Desktop file = Desktop.getDesktop();
                                    file.open(filedesktop);//opens the file from the desktop
                                } catch (IOException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }

                           
                        }
                    });

                } else {
                    JOptionPane.showMessageDialog(null, "No statistics to dispaly!");
                }

                //here
            }

        });

        spinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                status = false;
                status = true;
                matches = matches + 1;
                ongoing = 1;

                if (bet > 0) {

                    Thread A = new Thread(new Runnable() {

                        @Override
                        public void run() {

                            try {
                                while (status) {
                                    R.spin();
                                    ImageIcon img2 = new ImageIcon(R.location);
                                    lbl2.setIcon(img2);
                                    instance1 = R.value;
                                    Thread.sleep(100);
                                }

                            } catch (InterruptedException e) {

                            }
                        }
                    });

                    Thread B = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (status) {
                                    R1.spin();
                                    ImageIcon img4 = new ImageIcon(R1.location);
                                    lbl1.setIcon(img4);
                                    instance2 = R1.value;
                                    Thread.sleep(100);
                                }
                            } catch (InterruptedException e) {

                            }
                        }
                    });
                    Thread C = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (status) {
                                    R2.spin();
                                    ImageIcon img1 = new ImageIcon(R2.location);
                                    lbl3.setIcon(img1);
                                    instance3 = R2.value;
                                    Thread.sleep(100);
                                }
                            } catch (InterruptedException e) {

                            }
                        }

                    });
                    A.start();
                    B.start();
                    C.start();
                } else {
                    JOptionPane.showMessageDialog(null, "please place a bet");
                }

            }

        });

        lbl1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                status = false;
                creditBetted.setText("Credit Betted: " + bet);
                R3.compare(instance1, instance2, instance3, bet);

                bet = R3.bet1;
                lblBet.setText("" + bet);

                lostCR = R3.lostCr;
                creditLost.setText("Credit Lost: " + lostCR);

                wonCR = R3.total;
                totalCR = totalCR + R3.total;
                lostCR = R3.lostCr;
                Win = R3.wins;

                credit = credit + wonCR;
                lblCredit.setText("Credit Area: " + credit);

                avg = wonCR / matches;
                stats = 1;
                avgCredit.setText("Average per game: " + avg);
                ongoing = 0;
            }
        });
        lbl2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                status = false;
                creditBetted.setText("Credit Betted: " + bet);
                R3.compare(instance1, instance2, instance3, bet);

                bet = R3.bet1;
                lblBet.setText("" + bet);

                lostCR = R3.lostCr;
                creditLost.setText("Credit Lost: " + lostCR);

                wonCR = R3.total;
                totalCR = totalCR + R3.total;
                lostCR = R3.lostCr;
                Win = R3.wins;

                credit = credit + wonCR;
                lblCredit.setText("Credit Area: " + credit);

                avg = wonCR / matches;
                stats = 1;
                avgCredit.setText("Average per game: " + avg);
                ongoing = 0;
            }
        });
        lbl3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                status = false;
                creditBetted.setText("Credit Betted: " + bet);
                R3.compare(instance1, instance2, instance3, bet);

                bet = R3.bet1;
                lblBet.setText("" + bet);

                lostCR = R3.lostCr;
                creditLost.setText("Credit Lost: " + lostCR);

                wonCR = R3.total;
                totalCR = totalCR + R3.total;
                lostCR = R3.lostCr;
                Win = R3.wins;

                credit = credit + wonCR;
                lblCredit.setText("Credit Area: " + credit);

                avg = wonCR / matches;
                stats = 1;
                avgCredit.setText("Average per game: " + avg);
                ongoing = 0;

            }
        });

        window.setVisible(true);
    }

}
