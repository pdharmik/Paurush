package com.lexmark.contract;

import java.io.Serializable;

public class DocumentUserListContract implements Serializable {

    private static final long serialVersionUID = 1L;
    private int definitionId;

    public int getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(int definitionId) {
        this.definitionId = definitionId;
    }

}
