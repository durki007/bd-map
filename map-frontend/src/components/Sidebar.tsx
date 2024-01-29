'use client';

import { useMutation, useQueryClient } from '@tanstack/react-query';
import { Fragment, useEffect, useState } from 'react';
import { css } from 'styled-system/css';
import { Flex, HStack, Stack, VStack } from 'styled-system/jsx';
import { Changeset, closeChangeset } from '~/api/changeset';
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
  handleChangesetClick: any;
}) => {
  const object = props.object;

  const [currentObject, setCurrentObject] = useState(object);
  const queryClient = useQueryClient();

  useEffect(() => {
    setCurrentObject(object);
  }, [object]);

  const closeChangesetMutation = useMutation({
    mutationFn: closeChangeset,
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['changesets'],
      });
    },
  });

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

  const renderNode = (obj: Node) => {
    const isDisabled = obj.blockedBy !== 0;

    return (
      <Stack bg="bg.muted" padding="4">
        <Heading textStyle={'xl'}>Node</Heading>
        <Text as="p">id: {obj.id}</Text>
        <NumberInput
          defaultValue={obj.posX.toString()}
          disabled={isDisabled}
          onValueChange={(details) =>
            ((currentObject as Node).posX = details.valueAsNumber)
          }
        >
          posX
        </NumberInput>
        <NumberInput
          defaultValue={obj.posY.toString()}
          disabled={isDisabled}
          onValueChange={(details) =>
            ((currentObject as Node).posY = details.valueAsNumber)
          }
        >
          posY
        </NumberInput>
        <Text as="p">blockedBy: {isDisabled ? 'none' : obj.blockedBy}</Text>
        <Stack gap="1.5" width="2xs">
          <FormLabel htmlFor="nodeType">nodeType</FormLabel>
          <Input
            id="nodeType"
            defaultValue={obj.nodeType}
            disabled={isDisabled}
            onChange={(e) =>
              ((currentObject as Node).nodeType = e.target.value)
            }
          />
        </Stack>
        <Text as="p">
          timestamp: {new Date(obj.timestamp).toLocaleString('pl-PL')}
        </Text>
      </Stack>
    );
  };

  const renderWay = (obj: MapWay) => {
    const isDisabled = obj.blockedBy !== 0;

    return (
      <Stack bg="bg.muted" padding="4">
        <Heading textStyle={'xl'}>Way</Heading>
        <Text as="p">id: {obj.id}</Text>
        <Stack gap="1.5" width="2xs">
          <FormLabel htmlFor="name">name</FormLabel>
          <Input
            id="name"
            defaultValue={obj.name}
            disabled={isDisabled}
            onChange={(e) => ((currentObject as MapWay).name = e.target.value)}
          />
        </Stack>
        <Text as="p">blockedBy: {isDisabled ? 'none' : obj.blockedBy}</Text>
        <Stack gap="1.5" width="2xs">
          <FormLabel htmlFor="wayType">wayType</FormLabel>
          <Input
            id="wayType"
            defaultValue={obj.wayType}
            disabled={isDisabled}
          />
        </Stack>
        <Text as="p">
          timestamp: {new Date(obj.timestamp).toLocaleString('pl-PL')}
        </Text>
      </Stack>
    );
  };

  const renderWayNode = (obj: MapWayNode) => {
    const isDisabled = obj.node1.blockedBy !== 0;

    return (
      <Stack bg="bg.muted" padding="4">
        <Heading textStyle={'xl'}>WayNode</Heading>
        <Text as="p">id: {obj.id}</Text>
        <NumberInput
          defaultValue={obj.id.toString()}
          min={0}
          formatOptions={{ maximumFractionDigits: 0 }}
          disabled={isDisabled}
        >
          wayId
        </NumberInput>
        <NumberInput
          defaultValue={obj.node1.id.toString()}
          min={0}
          formatOptions={{ maximumFractionDigits: 0 }}
          disabled={isDisabled}
        >
          node1Id
        </NumberInput>
        <NumberInput
          defaultValue={obj.node2.id.toString()}
          min={0}
          formatOptions={{ maximumFractionDigits: 0 }}
          disabled={isDisabled}
        >
          node2Id
        </NumberInput>
      </Stack>
    );
  };

  console.log('currentObject', currentObject);

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

  const handleClose = async () => {
    await closeChangesetMutation.mutate({ id: props.changeset.id });
    props.handleChangesetClick(false);
  };

  const saveNode = () => {
    const node = currentObject as Node;

    editNodeMutation.mutate({
      nodeId: node.id,
      changesetId: props.changeset.id,
      posX: node.posX,
      posY: node.posY,
    });
  };

  const saveMapWay = () => {
    const way = currentObject as MapWay;

    editWayMutation.mutate({
      wayId: way.id,
      changesetId: props.changeset.id,
      name: way.name,
      wayType: way.wayType,
    });
    editWayNodeMutation.mutate({
      wayNodeId: way.wayNodes[0].id,
      changesetId: props.changeset.id,
      wayId: way.id,
      node1Id: way.wayNodes[0].node1.id,
      node2Id: way.wayNodes[0].node2.id,
    });
    editNodeMutation.mutate({
      nodeId: way.wayNodes[0].node1.id,
      changesetId: props.changeset.id,
      posX: way.wayNodes[0].node1.posX,
      posY: way.wayNodes[0].node1.posY,
    });
    editNodeMutation.mutate({
      nodeId: way.wayNodes[0].node2.id,
      changesetId: props.changeset.id,
      posX: way.wayNodes[0].node2.posX,
      posY: way.wayNodes[0].node2.posY,
    });
  };

  const handleSave = () => {
    if (isNode(currentObject)) {
      saveNode();
    } else if (isMapWay(currentObject)) {
      saveMapWay();
    }
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
        <Button onClick={handleClose}>Zamknij zbiór zmian</Button>
        <Button onClick={handleSave}>Zapisz</Button>
      </VStack>
      <Stack gap="14">{renderFields()}</Stack>
    </Flex>
  );
};
