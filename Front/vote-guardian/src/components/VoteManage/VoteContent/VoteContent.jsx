import VoteManageHeader from "../VoteHeader/VoteManageHeader"
import VoteList from "./VoteList/VoteList"
import { useSetRecoilState } from 'recoil';
import { createVoteListState } from "@/recoil/atoms/createVoteListState";
import { useEffect } from "react";

export default function VoteContent() {
    const setVoteManageList = useSetRecoilState(createVoteListState);
    
    useEffect(() => {
        getCreateVoteList();
    }, [])
    //예정, 진행, 종료
    function getCreateVoteList() {
        setVoteManageList([
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
        ])
    }
    return(
        <>
            <VoteManageHeader/>
            <VoteList/>
        </>
    )
}