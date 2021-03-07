package com.vaadin.artur.datausecases.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

// This class should be removed once https://github.com/vaadin/spring/issues/729 is
// fixed

@Getter
@Setter
public class FakePageable implements Pageable {
    private FakeSort sort;
    private int pageNumber;
    private int pageSize;

    public FakePageable(int pageNumber, int pageSize, FakeSort sort) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    @Override
    public Pageable next() {
        return new FakePageable(getPageNumber() + 1, getPageSize(), getSort());
    }

    @Override
    public Pageable first() {
        return new FakePageable(0, getPageSize(), getSort());
    }

    @Override
    public long getOffset() {
        return (long) getPageNumber() * (long) getPageSize();
    }

    @Override
    public Pageable previousOrFirst() {
        return getPageNumber() == 0 ? this : new FakePageable(getPageNumber() - 1, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return getPageNumber() > 0;
    }
    // export type GridSorterDirection='asc'|'desc'| null;

    // export

    // interface GridSorter {
    // path: string;
    // direction: GridSorterDirection;
}
