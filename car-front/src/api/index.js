import axios from 'axios';
const baseUrl = "http://192.168.199.182/"

export const login = query => {
    return axios({
        url: baseUrl + 'user/login/',
        method: 'post',
        params: query
    });
};

export const register = query => {
    return axios({
        url: baseUrl + 'user/register/',
        method: 'post',
        params: query
    });
};

export const myInfo = () => {
    return axios({
        url: baseUrl + 'user/my/info/',
        method: 'post'
    });
};

export const updatePassword = query => {
    return axios({
        url: baseUrl + 'user/password/update/',
        method: 'post',
        params: query
    });
};

export const carList = query => {
    return axios({
        url: baseUrl + 'carModel/list/',
        method: 'post',
        params: query
    });
};

export const carDetail = query => {
    return axios({
        url: baseUrl + 'carModel/detail/',
        method: 'post',
        params: query
    });
};

export const preOrder = query => {
    return axios({
        url: baseUrl + 'carModel/preOrder/',
        method: 'post',
        params: query
    });
};

export const cancelPreOrder = query => {
    return axios({
        url: baseUrl + 'carModel/cancelPreOrder/',
        method: 'post',
        params: query
    });
};

export const getMyPreOrderInfo = query => {
    return axios({
        url: baseUrl + 'carModel/getMyPreOrderInfo/',
        method: 'post',
        params: query
    });
};