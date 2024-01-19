import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import { css } from 'styled-system/css';
import './globals.css';
import Providers from './providers';

const inter = Inter({ subsets: ['latin'] });

export const metadata: Metadata = {
  title: 'OSMClone',
  description: 'Klon OSM',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <div
          className={css({
            width: '100%',
            height: '100vh',
            bgColor: 'bg.emphasized',
          })}
        >
          <Providers>{children}</Providers>
        </div>
      </body>
    </html>
  );
}
