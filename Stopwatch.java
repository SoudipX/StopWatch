import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stopwatch {
    JFrame frame;
    JLabel hourLabel, minuteLabel, secondLabel, milliLabel;
    JToggleButton startStopButton;
    JButton resetButton;
    Timer timer;
    int hours, minutes, seconds, milliseconds;
    boolean isRunning;

    public Stopwatch() {
        frame = new JFrame();
        frame.setTitle("Stopwatch");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.CYAN);

        JPanel displayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        displayPanel.setBackground(Color.CYAN);

        JLabel titleLabel = new JLabel("Stop Watch");
        titleLabel.setForeground(Color.BLACK);
        displayPanel.add(titleLabel);

        hourLabel = new JLabel("00");
        minuteLabel = new JLabel("00");
        secondLabel = new JLabel("00");
        milliLabel = new JLabel("00");

        displayPanel.add(hourLabel);
        displayPanel.add(new JLabel(":"));
        displayPanel.add(minuteLabel);
        displayPanel.add(new JLabel(":"));
        displayPanel.add(secondLabel);
        displayPanel.add(new JLabel(":"));
        displayPanel.add(milliLabel);

        frame.add(displayPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        startStopButton = new JToggleButton("Start");
        resetButton = new JButton("Reset");
        buttonPanel.add(startStopButton);
        buttonPanel.add(resetButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        startStopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (startStopButton.isSelected()) {
                    startStopButton.setText("Stop");
                    start();
                } else {
                    startStopButton.setText("Start");
                    stop();
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                milliseconds++;
                if (milliseconds == 100) {
                    milliseconds = 0;
                    seconds++;
                    if (seconds == 60) {
                        seconds = 0;
                        minutes++;
                        if (minutes == 60) {
                            minutes = 0;
                            hours++;
                        }
                    }
                }
                updateLabels();
            }
        });
    }

    void start() {
        timer.start();
        isRunning = true;
    }

    void stop() {
        timer.stop();
        isRunning = false;
    }

    void reset() {
        timer.stop();
        hours = minutes = seconds = milliseconds = 0;
        updateLabels();
        if (isRunning) {
            startStopButton.setSelected(false);
            startStopButton.setText("Start");
        }
    }

    void updateLabels() {
        hourLabel.setText(String.format("%02d", hours));
        minuteLabel.setText(String.format("%02d", minutes));
        secondLabel.setText(String.format("%02d", seconds));
        milliLabel.setText(String.format("%02d", milliseconds));
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Stopwatch stopwatch = new Stopwatch();
                stopwatch.setVisible(true);
            }
        });
    }
}