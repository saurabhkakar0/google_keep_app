package com.intuit.app.dao.label;

import com.intuit.app.models.Label;

public interface ILabelDao {

    void insertLabel(Label label);
    void updateLabel(Label labelId);

}
