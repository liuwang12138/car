// import axios from '../utils/axios';
import axios from 'axios';
//export const baseUrl = "http://192.168.199.182/"
export const baseUrl = "http://localhost/"

export const fetchData = query => {
    return axios({
        url: './table.json',
        method: 'get',
        params: query
    });
};

export const login = query => {
    return axios({
        url: baseUrl + 'admin/login/',
        method: 'post',
        params: query
    });
};

export const orderList = query => {
    return axios({
        url: baseUrl + 'admin/order/record/list/',
        method: 'post',
        params: query
    });
};

export const carModel = query => {
    return axios({
        url: baseUrl + 'carModel/list/',
        method: 'post',
        params: query
    });
};

export const carModelInsert = query => {
    return axios({
        url: baseUrl + 'admin/carModel/insert/',
        method: 'post',
        params: query
    });
};

export const carModelDel = query => {
    return axios({
        url: baseUrl + 'admin/carModel/delete/by/id',
        method: 'post',
        params: query
    })
}

export const userList = query => {
    return axios({
        url: baseUrl + 'admin/user/list/',
        method: 'post',
        params: query
    });
};

export const userInsert = query => {
    return axios({
        url: baseUrl + 'admin/user/insert/',
        method: 'post',
        params: query
    });
};

export const userDelete = query => {
    return axios({
        url: baseUrl + 'admin/user/delete/',
        method: 'post',
        params: query
    });
};

export const getUser = query => {
    return axios({
        url: baseUrl + 'admin/user/get/by/id',
        method: 'post',
        params: query
    });
};

export const userUpdate = query => {
    return axios({
        url: baseUrl + 'admin/user/update/info/',
        method: 'post',
        params: query
    });
};


export const uploadFile = query => {
    return axios({
        url: baseUrl + 'file/upload/',
        method: 'post',
        params: query,
        headers: {
            'Content-Type' : 'multipart/form-data'
        },
    });
};

export const getAfterSaleServiceList = query => {
    return axios({
        url: baseUrl + 'admin/after/sale/service/list/',
        method: 'post',
        params: query
    });
};
