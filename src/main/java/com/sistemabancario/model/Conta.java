package com.sistemabancario.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma conta banc�ria de um determinado {@link Cliente}, tendo os
 * seguintes requisitos:
 *
 * <ul>
 * <li>Uma conta n�o pode ser exclu�da se existirem movimenta��es.</li>
 * </ul>
 *
 * @author Manoel Campos da Silva Filho
 */
public class Conta implements Cadastro {

    private long id;

    /**
     * N�mero que identifica unicamente uma conta em uma determinada ag�ncia,
     * devendo estar no formato 99999-9. Se o n�mero n�o estiver no formato
     * indicado, o valor n�o pode ser armazenado e uma exce��o deve ser lan�ada
     * (R01). O n�mero da ag�ncia tem um d�gito verificador como no CPF, mas
     * isto � outro requisito n�o definido aqui.
     */
    private String numero;

    /**
     * Contas devem ser instanciadas como "Conta Corrente" e n�o como
     * "Poupan�a". (R02) O valor padr�o para atributos boolean � false, assim
     * n�o precisamos escrever c�digo adicional para definir "poupanca" como
     * false. Mas � preciso escrever o teste para verificar tal situa��o. Com
     * isto, buscamos detectar se uma altera��o no c�digo fizer com que este
     * requisito deixe de ser atendido.
     */
    private boolean poupanca;

    /**
     * Indica se a conta � especial ou n�o. Caso seja, ela pode ter
     * {@link #limite}.
     */
    private boolean especial;

    /**
     * Valor que o cliente possui na conta, sem incluir o {@link #limite}.
     */
    private double saldo;

    /**
     * Limite da conta: valor que o cliente pode utilizar al�m do {@link #saldo}
     * dispon�vel. Somente contas especiais podem ter limite, ou seja, o limite
     * de contas "n�o especiais" n�o pode ser maior que zero (R03).
     */
    private double limite;

    /**
     * Hist�rico de movimenta��es da conta. Deve ser inicializado com uma lista
     * vazia. Sem isto, ao tentar utilizar a lista, dar� o erro
     * NullPointerException. Um teste deve verificar se, ap�s instanciar uma
     * conta usando qualquer um dos construtores, a lista de movimenta��es n�o �
     * nula, chamando o m�todo {@link #getMovimentacoes()}. (R04)
     */
    private List<Movimentacao> movimentacoes = new ArrayList<>();

    public Conta() {
        // TODO: Voc� precisa implementar este m�todo
    }

    public Conta(Agencia agencia, boolean especial, final double limite) {
        // TODO: Voc� precisa implementar este m�todo
    }

    /**
     * Retorna a lista de movimenta��es.
     *
     * @return
     */
    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    /**
     * Adiciona uma nova movimenta��o � lista de {@link #movimentacoes}. (R05)
     * Se a movimenta��o estiver confirmada, seu valor deve ser:
     * <ul>
     * <li>somado ao saldo da conta caso o tipo da movimenta��o seja 'C';</li>
     * <li>subtra�da do saldo da conta caso o tipo da movimenta��o seja
     * 'D'.</li>
     * </ul>
     *
     * @param movimentacao {@link Movimentacao} a ser adicionada
     */
    public void addMovimentacao(Movimentacao movimentacao) {
        if(movimentacao.isConfirmada()){
            if(movimentacao.getTipo() == 'C')
                saldo += movimentacao.getValor();
            else if(movimentacao.getTipo() == 'D')
                saldo -= movimentacao.getValor();
        }
    }

    /**
     * Valor total dispon�vel na conta, representando o {@link #saldo} mais o
     * {@link #limite}. (R06)
     *
     * @return
     */
    public double getSaldoTotal() {
        return saldo + limite;
    }

    /**
     * Registra uma nova movimenta��o para retirar um determinado valor da
     * conta, caso o valor seja menor ou igual ao saldo total. Ap�s realizar um
     * saque, o saldo deve ser atualizado.
     *
     * <p>
     * Se for tentando realizar dois saques ao mesmo tempo com valor igual ao
     * saldo total, somente o primeiro saque deve ser permitido. Isto evita que
     * um cliente que possua dois cart�es da mesma conta tente retirar mais
     * dinheiro do que h� dispon�vel na conta. Ele poderia tentar fazer isso
     * solicitando a ajuda de outra pessoa. Assim, as duas pessoas poderiam
     * tentar ir em caixas diferentes ao mesmo tempo para tentar realizar um
     * saque em duplicidade.
     * </p>
     *
     * @param valor valor a ser sacado (deve ser um valor positivo)
     */
    public void saque(final double valor) {
        // TODO: Voc� precisa implementar este m�todo
    }

    /**
     * Adiciona uma nova movimenta��o de dep�sito em dinheiro tanto realizada
     * por um funcion�rio quanto em um caixa eletr�nico, que deve ser confirmada
     * automaticamente. Considera-se que todos os caixas eletr�nicos do banco
     * t�m capacidade de contar c�dulas.
     *
     * @param valor valor a ser depositado (deve ser um valor positivo)
     */
    public void depositoDinheiro(final double valor) {
        Movimentacao movimentacao = new Movimentacao(this);
        movimentacao.setConfirmada(true);
        movimentacao.setTipo('C');
        movimentacao.setValor(valor);
        saldo += valor;
        movimentacoes.add(movimentacao);
    }

    /**
     * Adiciona uma nova movimenta��o de dep�sito em cheque (que deve ser
     * confirmada posteriormente por um funcion�rio do banco).
     *
     * @param valor valor a ser depositado (deve ser um valor positivo)
     */
    public void depositoCheque(final double valor) {
        // TODO: Voc� precisa implementar este m�todo
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if(!numero.matches("\\d{5}-\\d")){
        throw new IllegalArgumentException("N�mero inv�lido. Deve estar no formato 99999-9");
        }
        this.numero = numero;
    }

    public boolean isPoupanca() {
        return poupanca;
    }

    public void setPoupanca(boolean poupanca) {
        this.poupanca = poupanca;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        if(!especial && limite > 0){
            throw new IllegalStateException("Somente contas especiais podem ter limite");
        }
        this.limite = limite;
    }
}
