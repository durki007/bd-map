import { Metadata } from 'next';
import dynamic from 'next/dynamic';
import { center } from 'styled-system/patterns';
import { getChangesets } from '~/api/changeset';
import { getMapArea } from '~/api/map-area';

const DynamicMap = dynamic(() => import('~/components/Mapa'), {
  loading: () => <p>loading...</p>,
  ssr: false,
});

export const metadata: Metadata = {
  title: 'OSMClone',
};

export default async function Home() {
  const mapAreaData = await getMapArea({
    maxX: 1000.0,
    minX: 0.0,
    maxY: 340.0,
    minY: 0.0,
  });

  const changesetsData = await getChangesets({ userId: 2 });

  return (
    <main
      className={center({
        height: '100%',
      })}
    >
      <DynamicMap mapArea={mapAreaData} changesets={changesetsData} />
    </main>
  );
}
