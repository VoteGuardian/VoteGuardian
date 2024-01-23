'use client'

import { useEffect, useState } from "react"
import VoteOne from "./VoteOne";
import './VoteList.scss';

export default function VoteList() {
    const [createVoteList, setCreateVoteList] = useState([]);

    function getCreateVoteList() {
        //api 요청
        setCreateVoteList([
            {
                id: 1, title:"OO대학교 학생회장 선거", startAt: '2023-04-15 11:00:00',
                finishAt: '2023-04-31 23:59:59', createdAt: '2023-04-01 10:23:42', state: 1
            },
            {
                id: 2, title:"OO대학교 XX학과 MT 날짜 투표", startAt: '2023-04-15 11:00:00',
                finishAt: '2023-04-31 23:59:59', createdAt: '2023-04-01 10:23:42', state: 2
            },
            {
                id: 3, title:"굉장히 긴 제목이 하나 필요한데 뭐라고 적을까", startAt: '2023-03-12 09:00:00',
                finishAt: '2023-03-12 11:59:59', createdAt: '2023-03-12 08:23:13', state: 3
            },
            {
                id: 4, title:"굉장히 긴 제목이 하나 필요한데 뭐라고 적을까", startAt: '2023-03-12 09:00:00',
                finishAt: '2023-03-12 11:59:59', createdAt: '2023-03-12 08:23:13', state: 3
            }
        ]);
    }

    useEffect(() => {
        getCreateVoteList();
    }, []);

    return (
        <div className="vote-list">
            {createVoteList.map((vote) => 
                <VoteOne title={vote.title} startAt={vote.startAt}
                finishAt={vote.finishAt} createdAt={vote.createdAt} state={vote.state}/>
            )}
        </div>
    )
}