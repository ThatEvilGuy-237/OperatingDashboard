import axios, { type AxiosInstance } from 'axios';


class EvilApiService {
  protected static apiClient: AxiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 5000,
    headers: {
      'Content-Type': 'application/json',
    },
  });
}

export default EvilApiService;