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
    String branch;                  // =${git.branch}
    String commitId;                // =${git.commit.id}
    String buildUserName;           // =${git.build.user.name}
    String buildUserEmail;          // =${git.build.user.email}
    String buildTime;               // =${git.build.time}
    String commitUserName;          // =${git.commit.user.name}
    String commitUserEmail;         // =${git.commit.user.email}
    String commitTime;              // =${git.commit.time}

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    public String getBuildUserName() {
        return buildUserName;
    }

    public void setBuildUserName(String buildUserName) {
        this.buildUserName = buildUserName;
    }

    public String getBuildUserEmail() {
        return buildUserEmail;
    }

    public void setBuildUserEmail(String buildUserEmail) {
        this.buildUserEmail = buildUserEmail;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getCommitUserName() {
        return commitUserName;
    }

    public void setCommitUserName(String commitUserName) {
        this.commitUserName = commitUserName;
    }

    public String getCommitUserEmail() {
        return commitUserEmail;
    }

    public void setCommitUserEmail(String commitUserEmail) {
        this.commitUserEmail = commitUserEmail;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    @Override
    public String toString() {
        String _return = "";
        _return += "Branch: " + getBranch() + "</br>";
        _return += "CommitId: " + getCommitId() + "</br>";
        _return += "Build UserName: " + getBuildUserName() + "</br>";
        _return += "Build Email: " + getBuildUserEmail() + "</br>";
        _return += "Build Time: " + getBuildTime() + "</br>";
        _return += "Commit UserName: " + getCommitUserName() + "</br>";
        _return += "Commit Email: " + getCommitUserEmail() + "</br>";
        _return += "Commit Time: " + getCommitTime() + "</br>";
        return _return;


    }
}
