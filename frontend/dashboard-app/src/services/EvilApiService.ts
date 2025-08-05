import axios, { type AxiosInstance, type AxiosRequestConfig, type AxiosResponse } from 'axios';
import CookieService from './CookieService';
import addGetLogging from './LogAxiosRequests';


class EvilApiService {

  protected static apiClient: AxiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 5000,
    headers: {
      'Content-Type': 'application/json',
    },
  });
  // constructor of creation of the class
  static { addGetLogging(this.apiClient) };

  static getHeader() {
    const token = CookieService.getCookie('token');
    return {
      Authorization: token ? `Bearer ${token}` : '',
    };
  }
}

export default EvilApiService;