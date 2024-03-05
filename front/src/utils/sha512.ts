import crypto from 'crypto';

export function sha512(str: string) {
  return crypto.createHash('sha512').update(str).digest('hex');
}