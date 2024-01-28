'use client';

import { useMutation, useQueryClient } from '@tanstack/react-query';
import { css } from 'styled-system/css';
import { Flex, HStack } from 'styled-system/jsx';
import { createChangeset } from '~/api/changeset';
import { Button } from './ui/button';
import { Heading } from './ui/heading';

export const SidebarChangeset = (props: { handleClick: any }) => {
  const queryClient = useQueryClient();

  const createChangesetMutation = useMutation({
    mutationFn: createChangeset,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['changesets'],
      });
    },
  });

  const handleClick = async () => {
    await createChangesetMutation.mutate({
      userId: 2,
    });

    props.handleClick(true);
  };

  return (
    <Flex
      className={css({
        justifyContent: 'center',
        alignItems: 'center',
        flexDir: 'column',
      })}
    >
      <HStack>
        <Heading textStyle={'2xl'} marginY="2">
          Zbiór zmian
        </Heading>
      </HStack>
      <Button onClick={handleClick}>Utwórz zbiór zmian</Button>
    </Flex>
  );
};
