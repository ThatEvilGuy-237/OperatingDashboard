// PrivilegeType enum
export enum PrivilegeType {
  READ_ACCESS = "READ_ACCESS",
  MANAGE_SERVER = "MANAGE_SERVER",
  WRITE_ACCESS = "WRITE_ACCESS",
  KICK_USERS = "KICK_USERS",
  BAN_USERS = "BAN_USERS",
  VIEW_LOGS = "VIEW_LOGS",
  EDIT_PROFILE = "EDIT_PROFILE"
}

// Privilege interface
export interface Privilege {
  id: number;
  displayName: string;
  type: PrivilegeType;
  description: string;
}

// Role interface
export interface Role {
  id: number;
  name: string;
  color: string | null;
  privileges: Privilege[];
}

// JobTitle interface
export interface JobTitle {
  id: number;
  title: string;
  emote: string;
}

// AccountDto interface
export interface AccountDto {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  roles: Role[];
  validated: boolean;
  locked: boolean;
  jobTitle: JobTitle | null;
  accountCreated: string;
  lastLogin: string | null;
}
