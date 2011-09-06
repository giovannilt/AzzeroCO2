package it.agilis.mens.azzeroCO2.shared.git;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 9/6/11
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class GitRepositoryStateModel extends BaseModel {
    public void setCommitId(String commitId) {
        set("commitId", commitId);
    }
}
