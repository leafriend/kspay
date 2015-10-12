package com.leafriend.kspay.receiver;

/**
 * 전문({@link Message})를 처리하는 인터페이스다. 이 인터페이스를 구현하여 데이터베이스에 기록하거나 결제 내역을 처리하는 업무를
 * 수행하면 된다.
 *
 * @author leafriend
 */
public interface MessageHandler {

    /**
     * 인수로 전문을 처리할 수 있는지 반환한다.
     *
     * @param message
     *            처리 가능 여부를 확인할 전문
     *
     * @return 처리 가능 여부
     */
    boolean isHandleable(Message message);

    /**
     * 전문을 처리한다. 구현 클래스의 용도에 맞춰 데이터베이스에 기록하거나 결제 내역을 처리하는 업무를 수행한다.
     *
     * @param message
     *            처리할 전문
     */
    void handle(Message message);

}
