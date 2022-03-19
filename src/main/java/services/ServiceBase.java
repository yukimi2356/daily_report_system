package services;

import javax.persistence.EntityManager;

import utils.DBUtil;

/**
 * DB接続に関わる共通処理を行うクラス
 */

public class ServiceBase {

    protected EntityManager em = DBUtil.createEntityManager();

    public void close() {
        if(em.isOpen()) {
            em.close();
        }
    }

}
