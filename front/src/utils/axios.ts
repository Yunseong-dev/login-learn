import axios, { AxiosResponse } from "axios";

const apiServer: string = 'http://localhost:8080';

export const customAxios = axios.create({
    baseURL: apiServer
});

export const fetcher = async (url: string): Promise<any> => {
    const response: AxiosResponse = await customAxios.get(url);
    return response.data;
};

export const tokenFetcher = (token: string) => {
    return (url: string) => {
        return customAxios
            .get(url, { headers: { 'Authorization': `Bearer ${token}` } })
            .then((response: AxiosResponse) => response.data);
    };
};

export const fetcherWithToken = async (token: string, url: string): Promise<any> => {
    const response: AxiosResponse = await customAxios.get(url, { headers: { 'Authorization': `Bearer ${token}` } });
    return response.data;
};
