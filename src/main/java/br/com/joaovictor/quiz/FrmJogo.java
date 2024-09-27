package br.com.joaovictor.quiz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class FrmJogo extends javax.swing.JFrame {

    private List<Question> questoes;
    private int indiceAtual;
    private JRadioButton[] opcoes;
    private ButtonGroup grupoOpcoes;
    private JButton botaoProximo;
    private int pontuacao;

    //Funções próprias
    private void carregarQuestoes(String arquivo) {
        System.out.println("Tentando carregar as questoes");

        try {
            // Se o arquivo existir e for usado
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;
            // Pula a leitura da 1 linha
            br.readLine();
            //Loop que vai ler todas as linhas do arquivo
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                String pergunta = partes[0];
                String[] alternativas = {partes[1], partes[2], partes[3], partes[4]};
                String respostaCorreta = partes[5];
                int respostaCorretaIndice = -1;
                //Atualizar o indice da resposta correta
                for(int i=0; i < alternativas.length;i++){
                    if(alternativas[i].equals(respostaCorreta)){
                        respostaCorretaIndice = i;
                    }
                }
                questoes.add(new Question(pergunta, alternativas, respostaCorretaIndice));
            }
        } catch (IOException ex) {
            // Se der erro, esses comandos serão executados
            System.out.println("Erro" + ex.getMessage());

        }
    }

    //Como mostrar a questão na tela?
    private void mostrarQuestao() {
        if (indiceAtual < questoes.size()) {
            Question questao = questoes.get(indiceAtual);
            labelPergunta.setText(questao.getQuestion());
            for (int i = 0; i < opcoes.length; i++) {
                opcoes[i].setText(questao.getAnswers()[i]);
                opcoes[i].setSelected(false);
            }
            botaoProximo.setText(indiceAtual == questoes.size()?"Terminar":"Próxima");
        }else{
            finalizarQuiz();
        }

    }
    
    //Função que encerra o Quiz
    private void finalizarQuiz(){
        String resultado = "Você acertou "
                + pontuacao +" de "
                + questoes.size()+" questões.";
        JOptionPane.showMessageDialog(this, 
                resultado, //Mensagem
                "Fim de Jogo",// Titulo da janela
                JOptionPane.INFORMATION_MESSAGE);//icone
        System.exit(0); // Fecha o jogo

    }
    
    //Metodo que verifica a resposta e dá pontuação
    private void verificarResposta(){
        Question questaoAtual = questoes.get(indiceAtual);
        for(int i=0; i<opcoes.length; i++){
            if(opcoes[i].isSelected() && i == questaoAtual.getCorrectAnswer()){
                pontuacao += 1;
                break;
            }
        }
    }
    
    /**
     * Construtor da classe Ele define os valores iniciais da tela quando o
     * aplicativo é aberto
     */
    public FrmJogo() {
        initComponents();
        questoes = new ArrayList<>();
        carregarQuestoes("questoes.csv");
        //labelPergunta.setText("Bem vindo ao Quiz!");
        setSize(480, 240);
        setTitle("Prova de Informática");
        indiceAtual = 0;
        pontuacao = 0;
        setLayout(new FlowLayout());

        //Configurar as opções de resposta
        opcoes = new JRadioButton[4];
        grupoOpcoes = new ButtonGroup();

        //Laço que mostra as 4 opções de resposta
        for (int i = 0; i < opcoes.length; i++) {
            opcoes[i] = new JRadioButton();
            grupoOpcoes.add(opcoes[i]);
            add(opcoes[i]);
        }//Fim do laço for...

        botaoProximo = new JButton("Proxima pergunta");
        //Criado a ação de clique do botão
        botaoProximo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarResposta();
                indiceAtual++;
                if(indiceAtual < questoes.size()){
                    mostrarQuestao();
                }else{
                    finalizarQuiz();
                }
            }
        });
        //Adicionar o botão na tela
        add(botaoProximo);
        mostrarQuestao();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPergunta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelPergunta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelPergunta.setText("Aqui irão aparecer as perguntas do quiz!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelPergunta, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelPergunta)
                .addContainerGap(305, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmJogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelPergunta;
    // End of variables declaration//GEN-END:variables
}
