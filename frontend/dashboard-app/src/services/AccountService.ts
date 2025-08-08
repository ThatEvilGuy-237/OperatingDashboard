// src/services/AccountService.ts
import type { PagedResult } from "../interfaces/PagedResults";
import type { AccountDto } from "../interfaces/Account";
import CookieService from "./CookieService";
import EvilApiService from "./EvilApiService";
// export interface AccountDto {
//   id: number;
//   username: string;
//   email: string;
//   firstName: string;
//   lastName: string;
// }
class AccountService extends EvilApiService {
  static async getAccounts(page = 0, size = 1): Promise<PagedResult<AccountDto>> {

    const response = await this.apiClient.get<PagedResult<AccountDto>>('/accounts', {
      params: { page, size },
      headers: this.getHeader()
    });
    return response.data;
  }

  static async getAccountById(id: number): Promise<AccountDto> {
    const response = await this.apiClient.get<AccountDto>(`/accounts/${id}`,{
      headers: this.getHeader()
      }
    );
    return response.data;
  }
  static async updateAccount(account: AccountDto): Promise<AccountDto> {
    return await this.apiClient.put(`/accounts/${account.id}`, account, {
      headers: this.getHeader()
    });
  }
  static async deleteAccount(id: number): Promise<AccountDto> {
    return await this.apiClient.delete(`/accounts/${id}`, {
      headers: this.getHeader()
    });
  }
}

export { AccountService };
