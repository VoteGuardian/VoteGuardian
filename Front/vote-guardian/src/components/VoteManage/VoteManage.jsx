import VoteManageHeader from "./VoteHeader/VoteManageHeader"
import VoteList from "./VoteContent/VoteList/VoteList"
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { createVoteListState, votePage, voteState, voteTotalPage } from "@/recoil/atoms/createVoteListState";
import { useEffect } from "react";
import { getCreateVoteList } from "@/app/api/vote/vote";

export default function VoteManage() {
    //생성한 투표 목록
    const setVoteManageList = useSetRecoilState(createVoteListState);
    //생성한 투표 목록이 가지는 전체 페이지 수
    const setVoteTotalPage = useSetRecoilState(voteTotalPage);
    //투표 상태와 현재 페이지 번호
    const state = useRecoilValue(voteState);
    const page = useRecoilValue(votePage);
    //이메일은 아직 더미
    const email = 'ko123@g.com';

    //회원이 생성한 투표 목록을 불러오기
    //투표 상태(전체, 예정, 진행, 종료)와 페이지가 바뀔 경우 다시 불러오기
    useEffect(() => {
        async function fetchVoteData() {
            const voteInfo = await getCreateVoteList(state, page-1, email);
            setVoteManageList(voteInfo.voteList);
            setVoteTotalPage(voteInfo.totalPageCnt);
        }
        
        fetchVoteData();
    }, [state, page])
    return(
        <>
            <VoteManageHeader/>
            <VoteList/>
        </>
    )
}