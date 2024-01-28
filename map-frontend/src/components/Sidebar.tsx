'use client';

import { useMutation, useQueryClient } from '@tanstack/react-query';
import { Fragment, useEffect, useState } from 'react';
import { css } from 'styled-system/css';
import { Flex, HStack, Stack, VStack } from 'styled-system/jsx';
import { Changeset } from '~/api/changeset';
import { MapWay, MapWayNode, isMapWay } from '~/api/map-area';
import { Node, editNode, isNode } from '~/api/nodes';
import { editWayNode } from '~/api/waynodes';
import { Way, editWay } from '~/api/ways';
import { Button } from './ui/button';
import { FormLabel } from './ui/form-label';
import { Heading } from './ui/heading';
import { Input } from './ui/input';
import { NumberInput } from './ui/number-input';
import { Text } from './ui/text';

export const Sidebar = (props: {
  object?: Node | MapWay;
  changeset: Changeset;
}) => {
  const object = props.object;

  const [currentObject, setCurrentObject] = useState(object);
  const queryClient = useQueryClient();

  useEffect(() => {
    setCurrentObject(object);
  }, [object]);

  const editWayMutation = useMutation({
    mutationFn: editWay,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['ways'],
      });
    },
  });

  const editNodeMutation = useMutation({
    mutationFn: editNode,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['nodes'],
      });
    },
  });

  const editWayNodeMutation = useMutation({
    mutationFn: editWayNode,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['wayNodes'],
      });
    },
  });

  const renderNode = (currentObject: Node) => {
    const isDisabled = currentObject.blockedBy !== 0;

    return (
      <Stack bg="bg.muted" padding="4">
        <Heading textStyle={'xl'}>Node</Heading>
        <Text as="p">id: {currentObject.id}</Text>
        <NumberInput
          defaultValue={currentObject.posX.toString()}
          disabled={isDisabled}
        >
          posX
        </NumberInput>
        <NumberInput
          defaultValue={currentObject.posY.toString()}
          disabled={isDisabled}
        >
          posY
        </NumberInput>
        <Text as="p">
          blockedBy: {isDisabled ? 'none' : currentObject.blockedBy}
        </Text>
        <Stack gap="1.5" width="2xs">
          <FormLabel htmlFor="nodeType">nodeType</FormLabel>
          <Input
            id="nodeType"
            defaultValue={currentObject.nodeType}
            disabled={isDisabled}
          />
        </Stack>
        <Text as="p">
          timestamp: {new Date(currentObject.timestamp).toLocaleString('pl-PL')}
        </Text>
      </Stack>
    );
  };

  const renderWay = (currentObject: MapWay) => {
    const isDisabled = currentObject.blockedBy !== 0;

    return (
      <Stack bg="bg.muted" padding="4">
        <Heading textStyle={'xl'}>Way</Heading>
        <Text as="p">id: {currentObject.id}</Text>
        <Stack gap="1.5" width="2xs">
          <FormLabel htmlFor="name">name</FormLabel>
          <Input
            id="name"
            defaultValue={currentObject.name}
            disabled={isDisabled}
          />
        </Stack>
        <Text as="p">
          blockedBy: {isDisabled ? 'none' : currentObject.blockedBy}
        </Text>
        <Stack gap="1.5" width="2xs">
          <FormLabel htmlFor="wayType">wayType</FormLabel>
          <Input
            id="wayType"
            defaultValue={currentObject.wayType}
            disabled={isDisabled}
          />
        </Stack>
        <Text as="p">
          timestamp: {new Date(currentObject.timestamp).toLocaleString('pl-PL')}
        </Text>
      </Stack>
    );
  };

  const renderWayNode = (currentObject: MapWayNode) => {
    const isDisabled = currentObject.node1.blockedBy !== 0;

    return (
      <Stack bg="bg.muted" padding="4">
        <Heading textStyle={'xl'}>WayNode</Heading>
        <Text as="p">id: {currentObject.id}</Text>
        <NumberInput
          defaultValue={currentObject.id.toString()}
          min={0}
          formatOptions={{ maximumFractionDigits: 0 }}
          disabled={isDisabled}
        >
          wayId
        </NumberInput>
        <NumberInput
          defaultValue={currentObject.node1.id.toString()}
          min={0}
          formatOptions={{ maximumFractionDigits: 0 }}
          disabled={isDisabled}
        >
          node1Id
        </NumberInput>
        <NumberInput
          defaultValue={currentObject.node2.id.toString()}
          min={0}
          formatOptions={{ maximumFractionDigits: 0 }}
          disabled={isDisabled}
        >
          node2Id
        </NumberInput>
      </Stack>
    );
  };

  const renderFields = () => {
    if (isNode(currentObject)) {
      return renderNode(currentObject);
    } else if (isMapWay(currentObject)) {
      return (
        <>
          {renderWay(currentObject)}
          {currentObject.wayNodes.map((wayNode) => (
            <Fragment key={wayNode.id}>
              {renderWayNode(wayNode)}
              {renderNode(wayNode.node1)}
              {renderNode(wayNode.node2)}
            </Fragment>
          ))}
        </>
      );
    }

    return null;
  };

  const handleSave = () => {
    const way = currentObject?.way as Way;
    const node1 = currentObject?.node1 as Node;
    const node2 = currentObject?.node2 as Node;

    editWayMutation.mutate({
      wayId: way.id,
      changesetId: 1,
      name: way.name!,
      wayType: way.wayType!,
    });
    editNodeMutation.mutate({
      nodeId: node1.id,
      changesetId: 1,
      posX: node1.posX,
      posY: node1.posY,
    });
    editNodeMutation.mutate({
      nodeId: node2.id,
      changesetId: 1,
      posX: node2.posX,
      posY: node2.posY,
    });
    editWayNodeMutation.mutate({
      wayNodeId: currentObject?.id as number,
      changesetId: 1,
      wayId: currentObject?.wayId as number,
      node1Id: currentObject?.node1Id as number,
      node2Id: currentObject?.node2Id as number,
    });
  };

  return (
    <Flex
      className={css({
        justifyContent: 'center',
        alignItems: 'center',
        flexDir: 'column',
        maxHeight: 'fit-content',
      })}
    >
      <Heading textStyle={'2xl'} marginY="2">
        Edytor
      </Heading>
      <VStack marginY="2">
        <Button onClick={() => console.log('te')}>Zamknij zbiór zmian</Button>
        <Button onClick={handleSave}>Zapisz</Button>
      </VStack>
      <Stack gap="14">{renderFields()}</Stack>
    </Flex>
  );
};
