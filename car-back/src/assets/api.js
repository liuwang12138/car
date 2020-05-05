import axios from "axios";

var baseUrl = 'http://10.10.11.31/CXFWebService/';

export const user = (params) => {
    return axios({
        url: baseUrl + 'page/interface/interfacelist.do',
        method: 'post',
        data: params
    })
}
