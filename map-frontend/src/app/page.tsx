import { ArrowRightIcon } from 'lucide-react';
import { Metadata } from 'next';
import Link from 'next/link';
import { css } from 'styled-system/css';
import { center, flex } from 'styled-system/patterns';
import { Button } from '~/components/ui/button';
import * as Card from '~/components/ui/card';

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
      <Card.Root
        className={css({
          maxWidth: '2xl',
        })}
      >
        <Card.Header>
          <Card.Title className={css({ fontSize: '2xl' })}>
            Witaj w OSMClone
          </Card.Title>
          <Card.Description>Prosty w obsłudze edytor map</Card.Description>
        </Card.Header>
        <Card.Body className={css({ mt: '2' })}>
          OSMClone to intuicyjny mapa z edytorem, stworzona z myślą o łatwości
          obsługi. Oferuje efektywne narzędzia do edycji. Zyskaj kontrolę nad
          swoim otoczeniem dzięki OSMClone!
        </Card.Body>
        <Card.Footer className={flex({ justifyContent: 'center' })}>
          <Link href="/map">
            <Button>
              Wejdź
              <ArrowRightIcon />
            </Button>
          </Link>
        </Card.Footer>
      </Card.Root>
    </main>
  );
}
