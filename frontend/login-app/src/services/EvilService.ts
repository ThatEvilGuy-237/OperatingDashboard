import axios, { type AxiosInstance } from 'axios';


class EvilApiService {
  protected static apiClient: AxiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api',
    timeout: 5000,
    headers: {
      'Content-Type': 'application/json',
    },
  });
}

export default EvilApiService;