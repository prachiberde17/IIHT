package com.assessment.reports.manager;

import java.util.ArrayList;
import java.util.List;

import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class NoTableLayoutManager extends ClassicLayoutManager {
    @Override
    protected List<AbstractColumn> getVisibleColumns() {
        return new ArrayList<>(); // hide all columns
    }
}