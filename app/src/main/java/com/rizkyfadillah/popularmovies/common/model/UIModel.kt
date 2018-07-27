package com.rizkyfadillah.popularmovies.common.model

/**
* Created by Rizky on 26/12/17.
*/

class UIModel<T> (val uiState: UIState, val data: T?, val message: String?) {

    enum class UIState {
        LOADING,
        ERROR,
        SUCCESS
    }

    companion object Factory {

        fun <T> success(data: T, message: String): UIModel<T> {
            return UIModel(UIState.SUCCESS, data, message)
        }

        fun <T> loading(): UIModel<T> {
            return UIModel(UIState.LOADING, null, null)
        }

        fun <T> error(message: String?): UIModel<T> {
            return UIModel(UIState.ERROR, null, message)
        }

    }

}