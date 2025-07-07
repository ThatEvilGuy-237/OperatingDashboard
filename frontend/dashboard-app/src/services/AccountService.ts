// src/services/AccountService.ts
import CookieService from "./CookieService";
import EvilApiService from "./EvilApiService";

export interface AccountDto {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
}

export interface PagedResult<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

class AccountService extends EvilApiService {
  static async getAccounts(page = 0, size = 1): Promise<PagedResult<AccountDto>> {

    const response = await this.apiClient.get<PagedResult<AccountDto>>('/accounts', {
      params: { page, size },
      headers: this.getHeader()
    });
    return response.data;
  }

  static async getAccountById(id: number): Promise<AccountDto> {
    const response = await this.apiClient.get<AccountDto>(`/accounts/${id}`,
      {
      headers: this.getHeader()
      }
    );
    return response.data;
  }
}

export { AccountService };
