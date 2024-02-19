import './VoteOne.scss';
import { FaCalendarAlt } from "react-icons/fa";
import { FaRegCalendarAlt } from "react-icons/fa";
import { useRecoilValue } from 'recoil';
import { createVoteListState, voteState } from "@/recoil/atoms/createVoteListState";
import { useEffect } from 'react';

export default function VoteOne() {
    const voteManageList = useRecoilValue(createVoteListState);

    const state = useRecoilValue(voteState);
    useEffect(() => {
    });

    return(
        <>
        {voteManageList !== null ? voteManageList.map((vote) =>
            <div key={vote.id} className='vote-info'>
                <div className='vote-one-top'>
                    {vote.state == 1 ? 
                        <div className="vote-type before">
                            <p>예정</p>
                        </div>
                    : (vote.state == 2) ?
                        <div className="vote-type progress">
                            <p>진행</p>
                        </div>
                    : <div className="vote-type done">
                        <p>종료</p>
                        </div>}
                </div>
                <div className='vote-content'>
                    <div className='vote-title'>
                        <p>{vote.title}</p>
                    </div>
                    <div className='vote-period-date'>
                        <div className='vote-period-top'>
                            <div className='vote-period-title'>
                                <FaCalendarAlt size="20" />
                                <p>투표 기간</p>
                            </div>
                            
                        </div>
                        <div className='vote-period-bottom'>
                            <p className='vote-period-start'>{vote.startAt.substr(0, 16)}</p>
                            <p className='vote-period-end'>~ {vote.finishAt.substr(0, 16)}</p>
                        </div>
                    </div>
                    <div className='vote-period-date'>
                        <div className='vote-period-top'>
                            <div className='vote-period-title'>
                                <FaRegCalendarAlt size="20"/>
                                <p>생성 일자</p>
                            </div>
                            
                        </div>
                        <div className='vote-period-bottom'>
                            <p>{vote.createdAt.substr(0, 16)}</p>
                        </div>
                    </div>
                    {/*
                    <div className='vote-created-date'>
                        <div className='vote-created-left'>
                            <FaRegCalendarAlt size="20"/>
                            <p className='vote-create'>생성 일자</p>
                        </div>
                        <div className='vote-created-right'>
                            <p>{vote.createdAt.substr(0, 16)}</p>
                        </div>
                    </div>
                    <div>
                        <div>
                        {vote.state == 1 ? 
                            <p>투표 시작까지</p>
                        : (vote.state == 2) ?<p>투표 종료까지</p>
                        : <div></div>
                        }
                        </div>
                    </div>
                    */}
                </div>
            </div>
        ) : (state == 0 ? 
            <div>
                <h1>투표가 없습니다</h1>
            </div>
        : (state == 1 ?
            <div>
                <h1>예정중인 투표가 없습니다</h1>
            </div>
        : (state == 2 ?
            <div>
                <h1>진행중인 투표가 없습니다</h1>
            </div>
        : <div>
            <h1>종료된 투표가 없습니다</h1>
        </div>)))}
        </>
    )
}