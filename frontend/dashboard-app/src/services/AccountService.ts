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
}

export { AccountService };
