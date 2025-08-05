import axios, { type AxiosInstance, type AxiosRequestConfig, type AxiosResponse } from 'axios';


function addGetLogging(instance: AxiosInstance) {
    const originalGet = instance.get.bind(instance);

    instance.get = function <T = any, R = AxiosResponse<T, any>, D = any>(
        url: string,
        config?: AxiosRequestConfig<D>
    ): Promise<R> {
        LogBefore('GET', url, !!(config && config.headers), config?.data);

        const result = originalGet<T, R, D>(url, config);

        result.then(response => {
            LogAfter('GET', response);
        }).catch(error => {
            LogAfter('GET', undefined, error);
        });

        return result;
    };
}

function LogBefore(typeCall: string, url: string, hasHeader: boolean, body?: any) {
    console.log(`[Axios ${typeCall}] Request URL: ${url}`);
    console.log(`- Header: ${hasHeader ? 'TRUE' : 'FALSE'}`);
    if (body !== undefined) {
        console.log(`- Body:`, body);
    }
}


function LogAfter(url: string, response?: any, error?: any) {
    console.log(`[Axios ${response.status}] Response URL:${url}`);
    console.log(`- data:`, response.data);
    if (error) {
        console.log(`- error:`, error);
    }
}

export default addGetLogging;