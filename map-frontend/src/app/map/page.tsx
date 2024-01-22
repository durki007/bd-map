import { Metadata } from 'next';
import dynamic from 'next/dynamic';
import { center } from 'styled-system/patterns';
import { getNodes } from '~/api/nodes';
import { getWayNodes } from '~/api/waynodes';
import { getWays } from '~/api/ways';

const DynamicMap = dynamic(() => import('~/components/Mapa'), {
  loading: () => <p>loading...</p>,
  ssr: false,
});

export const metadata: Metadata = {
  title: 'OSMClone',
};

export default async function Home() {
  const nodesData = await getNodes();
  const waysData = await getWays();
  const wayNodeData = await getWayNodes();

  return (
    <main
      className={center({
        height: '100%',
      })}
    >
      <DynamicMap nodes={nodesData} ways={waysData} wayNodes={wayNodeData} />
    </main>
  );
}
