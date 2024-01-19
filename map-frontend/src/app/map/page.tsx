import { Metadata } from 'next';
import dynamic from 'next/dynamic';
import { center } from 'styled-system/patterns';

const DynamicMap = dynamic(() => import('~/components/Mapa'), {
  loading: () => <p>loading...</p>,
  ssr: false,
});

export const metadata: Metadata = {
  title: 'OSMClone',
};

export default function Home() {
  return (
    <main
      className={center({
        height: '100%',
      })}
    >
      <DynamicMap />
    </main>
  );
}
