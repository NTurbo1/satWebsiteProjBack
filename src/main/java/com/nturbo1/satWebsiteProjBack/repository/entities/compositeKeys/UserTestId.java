package com.nturbo1.satWebsiteProjBack.repository.entities.compositeKeys;

import java.io.Serializable;
import java.util.Objects;

public class UserTestId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userId;
	private long testId;
	
	public UserTestId() {
	}

	public UserTestId(long userId, long testId) {
		this.userId = userId;
		this.testId = testId;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTestId that = (UserTestId) o;
        return userId == that.userId &&
                testId == that.testId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, testId);
    }
	
}
