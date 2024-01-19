import type { Metadata } from 'next';
import { Inter } from 'next/font/google';

const inter = Inter({ subsets: ['latin'] }); /*  */

export const metadata: Metadata = {
  title: 'OSMClone',
};

export default function MapLayout({ children }: { children: React.ReactNode }) {
  return <div>{children}</div>;
}
