package moodvisualizer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.net.URL;

class Browser {
    public Browser() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame("Rendered/Source Split");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                final JTextField address = new JTextField(
                    "http://google.com");
                final JEditorPane rendered = new JEditorPane();
                final JTextArea source = new JTextArea(20,30);

                address.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae) {
                            try {
                                URL url = new URL(address.getText());
                                rendered.setPage(url);
                            } catch(Throwable t) {
                                t.printStackTrace();
                                
                                String s = t.getMessage();
                                String news = "";
                                boolean isText = false;
                                for(int i=0; i<s.length();i++){
                                    char c=s.charAt(i);
                                    if(c=='<') isText=false;
                                    if(isText) news+=c;
                                    if(c=='>') isText=true;
                                }
                                source.setText(news);
                            }
                        }
                    });

                JButton showSource = new JButton("Show Source");
                showSource.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            
                                String s = rendered.getText();
                                String news = "";
                                boolean isText = false;
                                for(int i=0; i<s.length();i++){
                                    char c=s.charAt(i);
                                    if(c=='<') isText=false;
                                    if(isText) news+=c;
                                    if(c=='>') isText=true;
                                }
                                source.setText(news);
                        }
                    });

                JPanel gui = new JPanel(new BorderLayout(3,3));
                gui.setBorder(new EmptyBorder(5,5,5,5));
                gui.add( address, BorderLayout.NORTH );
                JSplitPane split = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT,
                    new JScrollPane(rendered),
                    new JScrollPane(source));
                gui.add(split);
                gui.add(showSource, BorderLayout.SOUTH);
                f.setContentPane(gui);

                f.pack();
                f.setLocationByPlatform(true);
                f.setVisible(true);
                split.setDividerLocation(.5);
            }
        });
    }
}