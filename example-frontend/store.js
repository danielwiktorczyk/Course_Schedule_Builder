import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import createLogger from 'redux-logger';
import allReducers from '../reducers';
import promise from 'redux-promise';

const logger = createLogger();

export default function configureStore(initialState) {
    return createStore(
        allReducers,
        initialState,
        applyMiddleware(thunk, logger, promise)
    );
}